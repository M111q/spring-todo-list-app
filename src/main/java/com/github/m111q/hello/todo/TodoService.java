package com.github.m111q.hello.todo;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    ResponseEntity<Todo> toogleTodo(Integer id) {
        Optional<Todo> todo = repository.findById(id);
        todo.ifPresent(t ->
        {
            t.setDone(!t.isDone());
            repository.save(t);
        });

        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }


}
