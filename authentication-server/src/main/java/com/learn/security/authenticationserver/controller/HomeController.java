package com.learn.security.authenticationserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("hello")
    public String hello(Authentication authentication){
        return "Hello \n" + authentication;
    }
}
