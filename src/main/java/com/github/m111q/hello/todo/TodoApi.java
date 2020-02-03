package com.github.m111q.hello.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TodoApi {

    private final Logger logger = LoggerFactory.getLogger(TodoApi.class);
    private TodoRepository repository;
    private TodoService service;

    @Autowired
    public TodoApi(TodoRepository repository, TodoService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        logger.info("Request got");

        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(repository.save(todo));
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> modifyTodo(@PathVariable Integer id) {
        Integer todoId = null;
        try {
            todoId = Integer.valueOf(id);
            return service.toogleTodo(id);
        } catch (NumberFormatException e) {
            logger.warn("wrong todo Id format (non numeric) " + id);
        } catch (StringIndexOutOfBoundsException e) {
            logger.warn("wrong todo Id format (empty string) " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
