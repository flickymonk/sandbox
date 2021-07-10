package com.alevel.todolist.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoOperations todoOperations;

    public TodoController(TodoOperations todoOperations) {
        this.todoOperations = todoOperations;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        return todoOperations.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Todo> getAllNotDone() {
        return todoOperations.getAllNotDone();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Todo todo, UriComponentsBuilder ucb) {
        Long id = todoOperations.save(todo);
        var params = Collections.singletonMap("id", id);
        return ResponseEntity.created(ucb.path("/todo/{id}").build(params)).body(params);
    }

    @PutMapping
    public void batchUpdate(@RequestBody Iterable<Todo> todos) {
        todoOperations.updateAll(todos);
    }

}
