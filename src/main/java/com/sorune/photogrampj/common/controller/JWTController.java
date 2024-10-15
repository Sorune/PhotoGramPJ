package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.util.jwt.CustomJWTException;
import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController("/api")
public class JWTController {

    @GetMapping("/token/refresh")
    public Map<String,Object> refreshToken(@RequestHeader("Authorization") String authHeader, String refreshToken){
        return new HashMap<>();
    }

    private boolean checkTime(Integer exp) {
        Date expDate= new Date((long)exp*1000);
        long gap = expDate.getTime() - System.currentTimeMillis();
        long leftMin = gap/(1000*60);
        return leftMin<60;
    }

    private boolean checkExpiredToken(String accessToken) {
        try {
            JwtUtil.parseToken(accessToken);
        } catch (CustomJWTException e) {
            if(e.getMessage().equals("Expired")){
                return true;
            }
        }
        return false;
    }

}
