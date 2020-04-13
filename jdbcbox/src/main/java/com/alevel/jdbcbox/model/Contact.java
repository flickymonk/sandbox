package com.alevel.jdbcbox.model;

import java.util.Objects;

public final class Contact {

    private final String phoneNumber;

    private final String name;

    private final String description;

    private final boolean active;

    public Contact(String phoneNumber, String name, String description) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.name = name;
        this.description = description;
        this.active = true;
    }

    public Contact(String phoneNumber, String name, String description, boolean active) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}
