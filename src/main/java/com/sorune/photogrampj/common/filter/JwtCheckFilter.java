package com.sorune.photogrampj.common.filter;

import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMember;
import com.sorune.photogrampj.member.member.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class JwtCheckFilter extends OncePerRequestFilter {
    private static final ModelMapper modelmapper = new ModelMapper();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals("OPTIONS")) {return true;}

        String path = request.getRequestURI();

        if (path.startsWith("/api/post/list")){return true;}

        return path.startsWith("/api/**/list");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authHeaderString = request.getHeader("Authorization");
        log.info("authHeaderString : {}",authHeaderString);
        // Authorization 헤더가 없거나 Bearer 토큰이 없으면 바로 필터 체인 넘기기
        if (authHeaderString == null || !authHeaderString.startsWith("Bearer ")) {
            log.info("Authorization Header is Null");
            filterChain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            log.info("parse accessToken");
            String accessToken = authHeaderString.substring(7);
            Map<String, Object> claims = JwtUtil.parseToken(accessToken);
            log.info("check filter claims : {}",claims);
            if(claims.get("email")!=null){
                MemberDTO member = modelmapper.map(claims, MemberDTO.class);
                log.info("member : {}", member.toString());

                authenticationToken = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getRole().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
            } else {
                RedisAnonymousMember member = modelmapper.map(claims,RedisAnonymousMember.class);
                log.info("member : {}", member.toString());

                authenticationToken = new UsernamePasswordAuthenticationToken(member, null, Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())));
            }
            log.info("authenticationToken : {}", authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e){
            filterChain.doFilter(request,response);
        }
    }
}
