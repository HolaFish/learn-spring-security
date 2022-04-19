package com.fxsh.learn.springsecurity.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 自定义验证逻辑
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /**
         * 一个只拥有默认用户，且不需要UserDetailService,以及PasswordEncoder的实现方式
         */
//        String username = authentication.getName();
//        String password = String.valueOf(authentication.getCredentials());
//        if("Tom".equals(username) && "123456".equals(password)){
//            return new UsernamePasswordAuthenticationToken(username,password, Arrays.asList());
//        }else{
//            System.out.println("密码或用户名错误");
//            throw new AuthenticationCredentialsNotFoundException("用户名或密码错误");
//        }
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        var user = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    /**
     * 验证该验证提供器是否支持当前的身份验证事件<code>Authentication</code>
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
