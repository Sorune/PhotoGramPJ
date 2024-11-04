package com.sorune.photogrampj.common.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e, HttpServletResponse response) {
        // 이미 응답이 커밋되었는지 확인
        if (response.isCommitted()) {
            // 커밋된 상태라면 로그를 남기거나 추가 동작을 생략
            log.warn("Response has already been committed. Skipping additional error handling.");
            return null;  // 혹은 필요에 따라 다른 처리
        }
        return new ResponseEntity<>("Error Message : "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
