package com.faz.demo.spring.security.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    private final String username;
    private final String password;

    @JsonCreator
    public Login(@JsonProperty("username") String username,
                 @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
