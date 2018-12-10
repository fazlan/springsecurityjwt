package com.faz.demo.spring.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;

class CustomPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder delegator;

    CustomPasswordEncoder(PasswordEncoder delegator) {
        this.delegator = delegator;
    }

    @Override
    public String encode(CharSequence charSequence) {
        return delegator.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String string) {
        return delegator.matches(charSequence, string);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return delegator.upgradeEncoding(encodedPassword);
    }
}
