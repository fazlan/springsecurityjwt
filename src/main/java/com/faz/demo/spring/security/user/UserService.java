package com.faz.demo.spring.security.user;

import com.faz.demo.spring.security.storage.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Store<User> store;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(Store<User> store, PasswordEncoder passwordEncoder) {
        this.store = store;
        this.passwordEncoder = passwordEncoder;
    }

    void save(User user) {
        User newUser = new User(
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                passwordEncoder.encode(user.getPassword()),
                user.getAuthorities());
        store.save(newUser, User.class);
    }

    public User get(String username) {
        return store.get(username, User.class);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
