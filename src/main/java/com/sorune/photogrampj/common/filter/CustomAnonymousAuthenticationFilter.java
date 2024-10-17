package com.sorune.photogrampj.common.filter;

import com.google.gson.Gson;
import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.common.util.jwt.RedisJwtRefreshToken;
import com.sorune.photogrampj.common.util.jwt.RedisJwtRefreshTokenRepository;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMember;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomAnonymousAuthenticationFilter extends OncePerRequestFilter {

    private final RedisAnonymousMemberRepository anonymousMemberRepository;
    private final RedisJwtRefreshTokenRepository refreshTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!= null) {
            log.info(auth.toString());
            log.info("authentication is exist");
            log.debug("JwtCheckFilter filterChain.doFilter called");
            filterChain.doFilter(request, response);
        }

        if(response.isCommitted()){
            log.info("response is Committed");
            filterChain.doFilter(request, response);
        }
        String authHeaderString = request.getHeader("Authorization");

        if (authHeaderString== null) {
            log.info("generate new anonymousMember");
            // 새로운 익명 사용자 생성 및 Redis에 저장
            RedisAnonymousMember anonymousMember = RedisAnonymousMember.builder()
                    .uuid(UUID.randomUUID().toString())
                    .nickName("GUEST")
                    .build();
            anonymousMemberRepository.save(anonymousMember);

            log.info("generate new Token");
            // AccessToken과 RefreshToken 발급
            String accessToken = JwtUtil.generateToken(anonymousMember,60*30);
            String refreshToken = JwtUtil.generateToken(anonymousMember,60*60*24);

            RedisJwtRefreshToken redisJwtRefreshToken = RedisJwtRefreshToken.builder()
                    .userId(anonymousMember.getUuid())
                    .refreshToken(refreshToken)
                    .build();
            refreshTokenRepository.save(redisJwtRefreshToken);

            Gson gson = new Gson();

            log.info("response write");
            String jsonString = gson.toJson(Map.of("ACCESS_TOKEN", accessToken, "REFRESH_TOKEN", refreshToken, "Anonymous", true));
            response.setHeader("Authorization", "Baerer " + accessToken);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //AccessToken이 없을 경우 생성하고 FilterChain을 중단시켜 Servlet으로 request가 전달되는 것을 중단
            return;
        }
        filterChain.doFilter(request, response);
    }
}
