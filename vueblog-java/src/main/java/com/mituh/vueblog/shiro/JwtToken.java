package com.mituh.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author
 * @Date 2020/11/24 19:58
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
        this.token=jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
