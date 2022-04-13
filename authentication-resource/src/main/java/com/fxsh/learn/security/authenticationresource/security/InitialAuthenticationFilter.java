package com.fxsh.learn.security.authenticationresource.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    @Value("${jwt.signing.key}")
    private String signingKey;

    public InitialAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("userName");
        String password = request.getHeader("password");
        if (Strings.isBlank(username) || Strings.isBlank(password)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication(username,password);
        /**
         * 委托给身份验证管理器，进行身份验证
         */
        authenticationManager.authenticate(authentication);

        /**
         * 身份验证成功，生成JWT令牌
         */
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setClaims(Map.of("username",username))
                .signWith(key)
                .compact();
        response.setHeader("Authorization",jwt);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // login 不过滤
        return !request.getServletPath().equals("/login");
    }
}
