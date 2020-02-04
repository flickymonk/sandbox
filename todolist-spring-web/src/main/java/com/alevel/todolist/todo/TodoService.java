package com.alevel.todolist.todo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoOperations {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> getAllNotDone() {
        return todoRepository.findByDoneFalse();
    }

    @Override
    public Long save(Todo todo) {
        return todoRepository.save(todo).getId();
    }

    @Override
    public void updateAll(Iterable<Todo> todos) {
        todoRepository.saveAll(todos);
    }
}
