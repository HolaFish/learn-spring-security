package com.fxsh.learn.security.authenticationresource.pojo;


import lombok.Data;

import java.util.List;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private List<Authority> authorities;
}
