package com.alevel.todolist.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        Long id = todoOperations.save(todo);
        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @PutMapping
    public void batchUpdate(@RequestBody Iterable<Todo> todos) {
        todoOperations.updateAll(todos);
    }

}
