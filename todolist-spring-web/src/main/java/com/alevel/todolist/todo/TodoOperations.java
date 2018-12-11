package com.alevel.todolist.todo;

import java.util.List;
import java.util.Optional;

public interface TodoOperations {

    Optional<Todo> getById(Long id);

    List<Todo> getAllNotDone();

    Long save(Todo todo);

    void updateAll(Iterable<Todo> todos);

}
