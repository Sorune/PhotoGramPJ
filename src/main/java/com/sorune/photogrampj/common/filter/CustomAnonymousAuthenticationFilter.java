package com.sorune.photogrampj.common.filter;

import com.google.gson.Gson;
import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.common.util.jwt.RedisJwtRefreshTokenRepository;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMember;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomAnonymousAuthenticationFilter extends OncePerRequestFilter {

    private final RedisAnonymousMemberRepository anonymousMemberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            filterChain.doFilter(request, response);
        }
        if(response.isCommitted()){
            filterChain.doFilter(request, response);
        }
        String authHeaderString = request.getHeader("Authorization");
        String refreshToken = request.getHeader("Refresh-Token");

        if (refreshToken == null || !JwtUtil.isValid(refreshToken)) {
            // 새로운 익명 사용자 생성 및 Redis에 저장
            RedisAnonymousMember anonymousMember = RedisAnonymousMember.builder()
                    .uuid(UUID.randomUUID().toString())
                    .nickName("GUEST")
                    .build();
            anonymousMemberRepository.save(anonymousMember);

            // AccessToken과 RefreshToken 발급
            String accessToken = JwtUtil.generateToken(anonymousMember,60*30);
            refreshToken = JwtUtil.generateToken(anonymousMember,60*60*24);

            Gson gson = new Gson();

            String jsonString = gson.toJson(Map.of("ACCESS_TOKEN", accessToken, "REFRESH_TOKEN", refreshToken, "Anonymous", true));
            response.setHeader("Authorization", "Baerer " + accessToken);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);
        }
        filterChain.doFilter(request, response);
    }
}
