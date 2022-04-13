package com.fxsh.learn.security.authenticationresource.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority {
    private Long id;
    private String authority;

    public String getAuthority() {
        return authority;
    }
}
