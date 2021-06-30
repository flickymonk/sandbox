package com.alevel.java.messagestore.service;

import com.alevel.java.messagestore.model.message.Message;
import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.alevel.java.messagestore.exception.MessageStoreExceptions.messageNotFound;

@Service
@Transactional
public class MessageService implements MessageCRUD {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponse create(SaveMessageRequest request) {
        var message = new Message(request.getTitle(), request.getText());
        return MessageResponse.fromMessage(messageRepository.save(message));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<MessageResponse> getById(UUID id) {
        return messageRepository.findById(id).map(MessageResponse::fromMessage);
    }

    @Override
    public void update(UUID id, SaveMessageRequest request) {
        var message = messageRepository.findById(id).orElseThrow(() -> messageNotFound(id));
        message.setText(request.getText());
        message.setTitle(request.getTitle());
        messageRepository.save(message);
    }

    @Override
    public Optional<MessageResponse> deleteById(UUID id) {
        var message = messageRepository.findById(id);
        message.ifPresent(messageRepository::delete);
        return message.map(MessageResponse::fromMessage);
    }
}
