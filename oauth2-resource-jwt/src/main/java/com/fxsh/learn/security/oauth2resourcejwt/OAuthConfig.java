package com.fxsh.learn.security.oauth2resourcejwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuthConfig extends ResourceServerConfigurerAdapter {
//
//    @Value("${jwt.key}")
//    private String jwtKey;
//    @Value("${key.public}")
//    private String publicKey;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(tokenStore());
//    }
//
//    /**
//     * 令牌存储器
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    /**
//     * 令牌转换器
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
////        jwtAccessTokenConverter.setSigningKey(jwtKey);
//        jwtAccessTokenConverter.setVerifierKey(publicKey);
//        return jwtAccessTokenConverter;
//    }
}
