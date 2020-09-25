package com.alevel.java.messagestore.controller;

import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.service.MessageCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static com.alevel.java.messagestore.exception.MessageStoreExceptions.messageNotFound;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageCRUD messageCRUD;

    public MessageController(MessageCRUD messageCRUD) {
        this.messageCRUD = messageCRUD;
    }

    @GetMapping("/{id}")
    public MessageResponse get(@PathVariable UUID id) {
        return messageCRUD.getById(id)
                .orElseThrow(() -> messageNotFound(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessageResponse create(@Valid @RequestBody SaveMessageRequest request) {
        return messageCRUD.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @Valid @RequestBody SaveMessageRequest request) {
        messageCRUD.update(id ,request);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable UUID id) {
        return messageCRUD.deleteById(id)
                .orElseThrow(() -> messageNotFound(id));
    }

}
