package com.sorune.photogrampj.common.filter;

import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.member.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Log4j2
public class JwtCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals("OPTIONS")) {return true;}

        String path = request.getRequestURI();

        if (path.startsWith("/api/post/list")){return true;}

        return path.startsWith("/api/**/list");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeaderString = request.getHeader("Authorization");

        try {
            String accessToken = authHeaderString.substring(7);

            MemberDTO member = JwtUtil.parseToken(accessToken);
            log.info("member : {}", member.toString());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getRole().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
            log.info("authenticationToken : {}", authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e){
            filterChain.doFilter(request,response);
        }
    }
}
