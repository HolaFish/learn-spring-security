package com.fxsh.learn.security.authenticationresource.config;

import com.fxsh.learn.security.authenticationresource.security.InitialAuthenticationFilter;
import com.fxsh.learn.security.authenticationresource.security.JwtAuthenticationFilter;
import com.fxsh.learn.security.authenticationresource.security.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }
    @Bean
    public InitialAuthenticationFilter initialAuthenticationFilter() throws Exception {
        return new InitialAuthenticationFilter(authenticationManager());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider); // 身份验证提供程序
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.addFilterAt(initialAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter,BasicAuthenticationFilter.class);
        http.authorizeHttpRequests()
                .anyRequest().authenticated(); //所有请求都需要被验证
    }

    /**
     * 将身份认证管理器添加到Spring的管理中
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
