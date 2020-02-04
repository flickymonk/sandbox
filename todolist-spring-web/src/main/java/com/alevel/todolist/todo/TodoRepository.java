package com.alevel.todolist.todo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByDoneFalse();

}
