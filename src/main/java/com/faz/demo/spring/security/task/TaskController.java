package com.faz.demo.spring.security.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('WRITE')")
    void create(@RequestBody List<TaskResource> tasks) {
        tasks.forEach(t -> service.save(new Task(t)));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    List<TaskResource> list() {
        return service.list().stream().map(TaskResource::new).collect(Collectors.toList());
    }
}

