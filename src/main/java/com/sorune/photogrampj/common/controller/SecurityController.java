package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class SecurityController {

    private final MemberService memberService;

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
