package com.alevel.textboard.model;

import java.util.ArrayList;
import java.util.List;

public final class Thread {

    private final User user;

    private final String title;

    private final String text;

    private final List<Comment> replies;

    public Thread(User user, String title, String text) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.replies = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Comment> getReplies() {
        return replies;
    }
}
