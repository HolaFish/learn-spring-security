package com.fxsh.learn.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UserManagerConfig {

    private final DataSource dataSource;

    public UserManagerConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 配置自定义用户管理服务
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        /**
         * 1：使用自定义的内存中用户管理服务
         */
//        var user = new User("Tom","123","read");
//        var userDetailsService = new InMemoryUserDetailService(List.of(user));
//        return userDetailsService;
        /**
         * 2： 使用Spring Security提供的JdbcUserDetailManager
         */
        String usersByUserNameQuery = "select username, password, enabled from users where username = ?";
        String authsByUserNameQuery = "select u.username, a.authority from users u join users_authorities ua on u.id = ua.account_id join authorities a on ua.authorities_id = a.id where u.username = ?";
        var userDetailService = new JdbcUserDetailsManager(dataSource);
        userDetailService.setUsersByUsernameQuery(usersByUserNameQuery);
        userDetailService.setAuthoritiesByUsernameQuery(authsByUserNameQuery);
        return userDetailService;
    }

    /**
     * 配置密码编码器
     * <br>
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
