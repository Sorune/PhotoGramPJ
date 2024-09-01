package com.sorune.photogrampj.common.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JwtUtil {
    private static ObjectMapper objectMapper;

    @Autowired
    public JwtUtil(ObjectMapper objectMapper) {JwtUtil.objectMapper = objectMapper;}


}
