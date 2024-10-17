package com.sorune.photogrampj.service;

import com.sorune.photogrampj.common.util.jwt.JwtUtil;
import groovy.util.logging.Log4j2;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpringBootTest
@Log4j2
public class JwtTest {


    private static final Logger log = LoggerFactory.getLogger(JwtTest.class);

    @Test
    public void parseToken(){
        JwtUtil jwtUtil = new JwtUtil();
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJjcmVhdGVEYXRlIjpbMjAyNCwxMCwxNyw2LDQ4LDYsODQ4MTM4MDAwXSwidXBkYXRlRGF0ZSI6WzIwMjQsMTAsMTcsNiw0OCw2LDg0ODEzODAwMF0sIm1lbUlkIjo3MDIsImVtYWlsIjoidGVzdDBAdGVzdC5jb20iLCJyb2xlIjpbIk1lbWJlciJdLCJkZWxldGVkIjpmYWxzZSwiaWF0IjoxNzI5MTMxMTIyLCJleHAiOjE3MjkyMzkxMjJ9.cpqG5iqIc2ZzeTiIRD6od5SngA1s3LRAh-Pl1TXe_Sc";
        SecretKey key = Keys.hmacShaKeyFor("123456789123456789123456789123456789123456789".getBytes(StandardCharsets.UTF_8));
        log.info(Jwts.parser().verifyWith(key).build().parse(token).getPayload().toString());
//        Map<String,Object> claims = Jwts.parser().build().parse(token).getPayload();
//        log.info(claims.toString());

    }
}
