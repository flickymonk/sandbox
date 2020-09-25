package com.alevel.java.messagestore.model.message;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    public Message() {
    }

    public Message(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Message(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(title, message.title) &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text);
    }
}
