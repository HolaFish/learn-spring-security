package com.fxsh.learn.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
@Deprecated // 使用多配置文件，职责分离
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置方式一
     * 不需要继承 WebSecurityConfigurerAdapter
     */
    /**
     * 自定义用户管理器
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var userDetailsService = new InMemoryUserDetailsManager();
//        var user = User.withUsername("Tom")
//                .password("123456")
//                .authorities("read")
//                .build();
//        userDetailsService.createUser(user);
//        return userDetailsService;
//    }
//
//    /**
//     * 自定义密码编码器
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    /**
     * 配置方式二
     * 继承 WebSecurityConfigurerAdapter,并重写方法
     */
    /**
     * 配置用户管理器和密码编码器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /**
//         * 方式1：
//         */
//        var userDetailService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("Tom")
//                .password("123456")
//                .authorities("read")
//                .build();
//        userDetailService.createUser(user);
//        //注册用户管理器 和 密码编码器
//        auth.userDetailsService(userDetailService).passwordEncoder(NoOpPasswordEncoder.getInstance());

        /**
         * 方式2：
         */
        auth.inMemoryAuthentication().withUser("Tom")
                .password("123456")
                .authorities("read")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
