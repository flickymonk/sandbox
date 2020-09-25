package com.alevel.java.messagestore.model.message;

import java.util.Objects;
import java.util.UUID;

public class MessageResponse {

    private UUID id;

    private String title;

    private String text;

    public static MessageResponse fromMessage(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getTitle(),
                message.getText()
        );
    }

    public MessageResponse() {
    }

    public MessageResponse(UUID id, String title, String text) {
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
        MessageResponse that = (MessageResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text);
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
