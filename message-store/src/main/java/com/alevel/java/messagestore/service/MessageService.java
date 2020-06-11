package com.alevel.java.messagestore.service;

import com.alevel.java.messagestore.entity.Message;
import com.alevel.java.messagestore.entity.request.SaveMessageRequest;
import com.alevel.java.messagestore.exception.MessageNotFoundException;
import com.alevel.java.messagestore.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MessageService implements MessageCRUD {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message create(SaveMessageRequest request) {
        var message = new Message();
        message.setTitle(request.getTitle());
        message.setText(request.getText());
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> getById(UUID id) {
        return messageRepository.findById(id);
    }

    @Override
    public void update(UUID id, SaveMessageRequest request) {
        var message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        message.setText(request.getText());
        message.setTitle(request.getTitle());
        messageRepository.save(message);
    }

    @Override
    public Optional<Message> deleteById(UUID id) {
        var message = messageRepository.findById(id);
        message.ifPresent(messageRepository::delete);
        return message;
    }
}
