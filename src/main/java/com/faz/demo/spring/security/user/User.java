package com.faz.demo.spring.security.user;

import com.faz.demo.spring.security.storage.Storable;

public class User implements Storable {

    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String[] authorities;

    User(User user) {
        this(user.email, user.firstname, user.lastname, user.password, user.authorities);
    }

    User(String email, String firstname, String lastname, String password, String[] authorities) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    @Override
    public String getId() {
        return email;
    }
}
