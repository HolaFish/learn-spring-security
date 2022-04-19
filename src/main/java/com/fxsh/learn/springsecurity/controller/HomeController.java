package com.fxsh.learn.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("greeting")
    public String greeting(Authentication authentication) {
        return "Hello :\n" + authentication;
    }
}
