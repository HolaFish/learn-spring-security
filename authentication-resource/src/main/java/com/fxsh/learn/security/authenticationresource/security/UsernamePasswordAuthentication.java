package com.fxsh.learn.security.authenticationresource.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义用户名，密码Token
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    /**
     * 创建正在验证的Token
     * @param principal
     * @param credentials
     */
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    /**
     * 创建已通过验证的Token
     * @param principal
     * @param credentials
     * @param authorities
     */
    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
