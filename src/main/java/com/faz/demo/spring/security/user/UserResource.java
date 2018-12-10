package com.faz.demo.spring.security.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResource extends User {

    @JsonCreator
    public UserResource(@JsonProperty("email") String email,
                 @JsonProperty("firstname") String firstname,
                 @JsonProperty("lastname") String lastname,
                 @JsonProperty("password") String password,
                 @JsonProperty("authorities") String... authorities) {
        super(email, firstname, lastname, password, authorities);
    }

    @Override
    @JsonProperty("email")
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    @JsonProperty("firstname")
    public String getFirstname() {
        return super.getFirstname();
    }

    @Override
    @JsonProperty("lastname")
    public String getLastname() {
        return super.getLastname();
    }

    @Override
    @JsonProperty("password")
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @JsonProperty("authorities")
    public String[] getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    @JsonIgnore
    public String getId() {
        return super.getId();
    }
}
