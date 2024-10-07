package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/logout")
    public String logout(){
        return "/";
    }
}
