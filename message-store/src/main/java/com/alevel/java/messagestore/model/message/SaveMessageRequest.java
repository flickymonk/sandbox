package com.alevel.java.messagestore.model.message;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SaveMessageRequest {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text content is mandatory")
    private String text;

    public SaveMessageRequest() {
    }

    public SaveMessageRequest(String title, String text) {
        this.title = title;
        this.text = text;
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
        SaveMessageRequest that = (SaveMessageRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text);
    }

    @Override
    public String toString() {
        return "SaveMessageRequest{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
