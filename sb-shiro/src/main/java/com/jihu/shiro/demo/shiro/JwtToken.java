package com.jihu.shiro.demo.shiro;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
