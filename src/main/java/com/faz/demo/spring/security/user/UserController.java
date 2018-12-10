package com.faz.demo.spring.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
class UserController {

    private final UserService service;

    @Autowired
    UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserResource user) {
        service.save(new User(user));
    }
}
