package com.alevel.java.messagestore.service;

import com.alevel.java.messagestore.entity.Message;
import com.alevel.java.messagestore.entity.request.SaveMessageRequest;

import java.util.Optional;
import java.util.UUID;

public interface MessageCRUD {

    Message create(SaveMessageRequest request);

    Optional<Message> getById(UUID id);

    void update(UUID id, SaveMessageRequest request);

    Optional<Message> deleteById(UUID id);

}
