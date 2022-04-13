package com.fxsh.learn.security.authenticationresource.security;

import com.fxsh.learn.security.authenticationresource.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 对身份验证服务的代理
 * @author LiuRunzhi
 * @date 2022/4/13 15:37
 * @since 1.0
 **/
@Component
public class AuthenticationServerProxy {

    private final RestTemplate restTemplate;
    @Value("${auth.server.base.url}")
    private String baseUrl;

    public AuthenticationServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 验证用户名密码
     * @param user
     * @return
     */
    public boolean auth(User user){
        String url = baseUrl + "/auth";
        var body = new User();
        body.setUsername(user.getUsername());
        body.setPassword(user.getPassword());
        var request = new HttpEntity<>(body);
        var response = restTemplate.postForEntity(url,request,Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
