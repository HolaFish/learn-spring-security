package com.learn.security.authenticationserver.controller;

import com.fxsh.learn.security.base.domain.User;
import com.learn.security.authenticationserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userDetailsService) {
        this.userService = userDetailsService;
    }
    @PostMapping
    public void auth(@RequestBody User user, HttpServletResponse response){
        try{
            userService.auth(user);
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
