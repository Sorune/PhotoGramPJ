package com.sorune.photogrampj.common.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMember;
import com.sorune.photogrampj.member.member.MemberDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Component
public class JwtUtil {
    private static final ModelMapper modelMapper = new ModelMapper();
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    private static final String SECRETE_KEY = "12345678912345678912345678912345678912345678956789123456789123456789123456789123456789";

    private static SecretKey key ;
    public JwtUtil(@Value("${spring.jwt.secretKey}")String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    //회원 정보를 토대로 토큰 생성
    public static String generateToken(MemberDTO member, int min){
//        SecretKey key = Keys.hmacShaKeyFor(JwtUtil.SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
        //토큰 생성시 패스워드 정보 초기화로 토큰에서 패스워드 제거
        member.setPassword(null);
        log.info(member.toString());
        Map<String,Object> claims = objectMapper.registerModule(new JavaTimeModule()).convertValue(member, Map.class);
        log.info("claims : {}",claims.toString());
        return Jwts.builder()
                .header().add(Map.of("type","JWT")).and()
                .claims().add(claims).and()
                // TypeToken생성시 Map<> 대신 HashMap 사용
                // ModelMapper가 Map타입을 해석을 못해서 발생하는 문제, 구체적인 타입 HashMap으로 해결
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
    }
    //익명 유저의 토큰 생성 메서드 오버로딩 활용
    public static String generateToken(RedisAnonymousMember member, int min){
//        SecretKey key = Keys.hmacShaKeyFor(SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
        member.setExpiredTime(min);
        Map<String,Object> claims = modelMapper.map(member , HashMap.class);
        log.info("claims : {}",claims);
        log.info(member.toString());
        return Jwts.builder()
                .header().add(Map.of("type","JWT")).and()
                .claims().add(modelMapper.map(member ,new TypeToken<HashMap<String,Object>>(){}.getType())).and()
                // TypeToken생성시 Map<> 대신 HashMap 사용
                // ModelMapper가 Map타입을 해석을 못해서 발생하는 문제, 구체적인 타입 HashMap으로 해결
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
    }

    public static Map<String,Object> parseToken(String token){

        try {
            log.info("parse token : {}",token);
//            SecretKey key = Keys.hmacShaKeyFor(JwtUtil.SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claim = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.registerModule(new JavaTimeModule()).convertValue(claim,Map.class);
            log.info("jwt util claims : {}",claims.toString());
            return claims;
        } catch (MalformedJwtException e) {
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException e) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException e) {
            throw new CustomJWTException("Invalid");
        } catch (JwtException e) {
            throw new CustomJWTException("JwtException");
        } catch (Exception e){
            log.info(e.getMessage());
            throw new CustomJWTException("Error : "+e.getMessage());
        }

    }

    public static String getUserIdFromToken(String token) {
        Map<String, Object> claims = parseToken(token);

        // UUID 또는 email을 가져옴
        String uuid = (String) claims.get("uuid");
        String email = (String) claims.get("email");

        // UUID와 email이 모두 null일 경우
        if (uuid == null && email == null) {
            throw new CustomJWTException("Invalid");
        }

        // UUID가 없을 경우 email을 반환
        return Optional.ofNullable(uuid).orElse(email);
    }


    public static boolean isValid(String token) {
        try {
            Jwts.parser()
//                    .verifyWith(Keys.hmacShaKeyFor(SECRETE_KEY.getBytes(StandardCharsets.UTF_8)))
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;  // 토큰이 유효하다면 true 반환
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // 유효하지 않은 경우 false 반환
        }
    }

}
