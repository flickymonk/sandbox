package com.alevel.java.messagestore.controller;

import com.alevel.java.messagestore.entity.Message;
import com.alevel.java.messagestore.entity.request.SaveMessageRequest;
import com.alevel.java.messagestore.exception.MessageNotFoundException;
import com.alevel.java.messagestore.service.MessageCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageCRUD messageCRUD;

    public MessageController(MessageCRUD messageCRUD) {
        this.messageCRUD = messageCRUD;
    }

    @GetMapping("/{id}")
    public Message get(@PathVariable UUID id) {
        return messageCRUD.getById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Message create(@RequestBody SaveMessageRequest request) {
        return messageCRUD.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody SaveMessageRequest request) {
        messageCRUD.update(id ,request);
    }

    @DeleteMapping("/{id}")
    public Message delete(@PathVariable UUID id) {
        return messageCRUD.deleteById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

}
