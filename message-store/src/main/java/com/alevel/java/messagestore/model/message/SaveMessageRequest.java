package com.alevel.java.messagestore.model.message;

import javax.validation.constraints.NotBlank;

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
}
