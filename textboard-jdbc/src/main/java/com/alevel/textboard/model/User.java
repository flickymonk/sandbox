package com.alevel.textboard.model;

public final class User {

    private final String email;

    private final String nickname;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
