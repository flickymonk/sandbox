package com.alevel.todolist.todo;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false, name = "is_done")
    private boolean done;

    public Todo(String text) {
        this.text = text;
        this.done = false;
    }

    public Todo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setText(String text) {
        this.text = text;
    }

}
