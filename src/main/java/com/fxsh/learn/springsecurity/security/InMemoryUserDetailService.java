package com.fxsh.learn.springsecurity.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;

/**
 * 自定义用户管理服务
 */
public class InMemoryUserDetailService implements UserDetailsService {
    private final List<UserDetails> users;

    /**
     * 初始化时确定用户列表
     * @param users
     */
    public InMemoryUserDetailService(List<UserDetails> users) {
        this.users = users;
    }

    /**
     * 唯一的职责就是根据用户名获取UserDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(userDetails -> userDetails.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
