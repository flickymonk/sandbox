package com.alevel.java.messagestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class MessageNotFoundException extends ResponseStatusException {

    public MessageNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Message with id " + id + " was not found");
    }
}
