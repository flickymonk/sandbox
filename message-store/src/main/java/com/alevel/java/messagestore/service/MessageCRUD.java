package com.alevel.java.messagestore.service;

import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;

import java.util.Optional;
import java.util.UUID;

public interface MessageCRUD {

    MessageResponse create(SaveMessageRequest request);

    Optional<MessageResponse> getById(UUID id);

    void update(UUID id, SaveMessageRequest request);

    Optional<MessageResponse> deleteById(UUID id);

}
