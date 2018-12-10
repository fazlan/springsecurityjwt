package com.faz.demo.spring.security.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

class TaskResource extends Task {

    @JsonCreator
    private TaskResource(@JsonProperty("name") String name, @JsonProperty("description") String description) {
        super(name, description);
    }

    TaskResource(Task task) {
        this(task.getName(), task.getDescription());
    }

    @JsonProperty("name")
    String getName() {
        return super.getName();
    }

    @JsonProperty("description")
    String getDescription() {
        return super.getDescription();
    }

    @JsonIgnore
    @Override
    public String getId() {
        return super.getDescription();
    }
}
