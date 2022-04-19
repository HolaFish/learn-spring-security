package com.fxsh.learn.security.oauth2resourcejwt;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("hello")
    public String hello(Authentication authentication){
        return "Hello" + authentication;
    }
}
