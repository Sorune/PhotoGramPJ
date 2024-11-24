package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.util.jwt.CustomJWTException;
import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import com.sorune.photogrampj.common.util.jwt.RedisJwtRefreshToken;
import com.sorune.photogrampj.common.util.jwt.RedisJwtRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController("/api")
public class JWTController {

    @Autowired
    private RedisJwtRefreshTokenRepository refreshTokenRepository;

    @GetMapping("/token/refresh")
    public ResponseEntity<Map<String,Object>> refreshToken(@RequestHeader("Authorization") String authHeader, String refreshToken){
        String memberId;
        try {
            memberId = JwtUtil.getUserIdFromToken(refreshToken);
        } catch (CustomJWTException e){
            return new ResponseEntity<>(Map.of(),HttpStatus.UNAUTHORIZED);
        }

        RedisJwtRefreshToken redisToken = refreshTokenRepository.findById(memberId).orElse(null);


        return new ResponseEntity<>(Map.of(), HttpStatus.OK);
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
