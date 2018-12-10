package com.faz.demo.spring.security.task;

import com.faz.demo.spring.security.storage.Storable;

class Task implements Storable {

    private final String name;
    private final String description;

    Task(Task task) {
        this(task.name, task.description);
    }

    Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return getName();
    }
}
