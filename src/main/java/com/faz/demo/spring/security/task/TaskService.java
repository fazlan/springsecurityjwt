package com.faz.demo.spring.security.task;

import com.faz.demo.spring.security.storage.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskService {

    private final Store<Task> store;

    @Autowired
    TaskService(Store<Task> store) {
        this.store = store;
    }

    void save(Task task) {
        store.save(task, Task.class);
    }

    List<Task> list() {
        return store.list(Task.class);
    }
}
