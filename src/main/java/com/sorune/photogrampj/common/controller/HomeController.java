package com.sorune.photogrampj.common.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
public class HomeController {


    @GetMapping({"/","/home"})
    public String photos(){
        log.info("Welecome!");
        return "photos/photos";
    }

    @GetMapping("/{id}")
    public String imgcelDetails(@PathVariable int id) {
        log.info("Get post : {}", id);
        return "photos/imgcel-details";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/chat")
    public String chat(){return "chat/chat";}
}
