package com.sorune.photogrampj.common.util.jwt;

import com.sorune.photogrampj.member.MemberDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Log4j2
@Component
public class JwtUtil {
    private static final ModelMapper modelMapper = new ModelMapper();
    private static final String SECRETE_KEY = "123456789123456789123456789123456789123456789";

    public static String generateToken(MemberDTO member, int min){
        SecretKey key = Keys.hmacShaKeyFor(JwtUtil.SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
        log.info(member.toString());

        return Jwts.builder()
                .header().add(Map.of("type","JWT")).and()
                .claims().add(modelMapper.map(member ,new TypeToken<Map<String,Object>>(){}.getType())).and()
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
    }

    public static MemberDTO parseToken(String token){
        SecretKey key;
        Map<String, Object> claims;
        try {
            key = Keys.hmacShaKeyFor(JwtUtil.SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
            claims = Jwts.parser().decryptWith(key).build().parseSignedClaims(token).getPayload();
        } catch (MalformedJwtException e) {
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException e) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException e) {
            throw new CustomJWTException("Invalid");
        } catch (JwtException e) {
            throw new CustomJWTException("JwtException");
        } catch (Exception e){
            throw new CustomJWTException("Error");
        }
        return modelMapper.map(claims, MemberDTO.class);

    }


}
