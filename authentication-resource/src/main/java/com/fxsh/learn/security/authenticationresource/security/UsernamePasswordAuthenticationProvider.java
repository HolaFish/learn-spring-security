package com.fxsh.learn.security.authenticationresource.security;

import com.fxsh.learn.security.authenticationresource.pojo.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy proxy;

    public UsernamePasswordAuthenticationProvider(AuthenticationServerProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(this.proxy.auth(user)){
            /**
             * 提供用户的密码，供身份验证管理器验证
             */
            return new UsernamePasswordAuthentication(username,password);
        }else{
            throw new BadCredentialsException("Bad Credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthentication.class);
    }
}
