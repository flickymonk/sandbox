package com.alevel.jdbcbox.model;

public record Contact(
        String phoneNumber,
        String name,
        String description,
        boolean active
) {

    public Contact(String phoneNumber, String name, String description) {
        this(phoneNumber, name, description, true);
    }

}
