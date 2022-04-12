package com.learn.security.authenticationserver.service;

import com.fxsh.learn.security.base.domain.User;

public interface UserService {

    /**
     * 验证用户名与密码
     * @param user
     */
    void auth(User user);
}
