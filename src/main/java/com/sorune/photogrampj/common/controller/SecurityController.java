package com.sorune.photogrampj.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class SecurityController {

    @PostMapping("/login")
    public String login(){
        return "member/login";
    }

    @GetMapping("/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerMember(){
        return new ResponseEntity<>(Map.of("Result","Success"),HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logout(){
        return "/";
    }
}
