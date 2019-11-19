package com.alevel.textboard.model;

import java.util.ArrayList;
import java.util.List;

public final class Comment {

    private final Persisted<Long, User> author;

    private final Persisted<Long, Thread> thread;

    private final Persisted<Long, Comment> parentComment;

    private final String text;

    private final List<Comment> replies;

    public Comment(
            Persisted<Long, User> author,
            Persisted<Long, Thread> thread,
            Persisted<Long, Comment> parentComment,
            String text
    ) {
        this.author = author;
        this.thread = thread;
        this.text = text;
        Thread t = thread.getValue();
        if (t != null) {
            t.getReplies().add(this);
        }
        this.replies = new ArrayList<>();
        this.parentComment = parentComment;
        if (parentComment != null) {
            Comment parent = parentComment.getValue();
            if (parent != null) {
                parent.replies.add(this);
            }
        }
    }

    public Persisted<Long, User> getAuthor() {
        return author;
    }

    public Persisted<Long, Thread> getThread() {
        return thread;
    }

    public Persisted<Long, Comment> getParentComment() {
        return parentComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public String getText() {
        return text;
    }
}
