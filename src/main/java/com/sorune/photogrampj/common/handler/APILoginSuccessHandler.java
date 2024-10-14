package com.sorune.photogrampj.common.handler;

import com.google.gson.Gson;
import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.member.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberService memberService;
    private final int ACCESS_TOKEN_EXPIRE = 60 * 30;
    private final int REFRESH_TOKEN_EXPIRE = 60 * 60 * 24;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String accessToken = JwtUtil.generateToken(memberService.findByEmail(authentication.getName()),ACCESS_TOKEN_EXPIRE);
        String refreshToken = JwtUtil.generateToken(memberService.findByEmail(authentication.getName()),REFRESH_TOKEN_EXPIRE);
        Gson gson = new Gson();

        String jsonString = gson.toJson(Map.of("ACCESS_TOKEN", accessToken, "REFRESH_TOKEN", refreshToken));
        response.setHeader("Authorization","Baerer "+ accessToken);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
