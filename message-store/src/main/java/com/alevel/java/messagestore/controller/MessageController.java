package com.alevel.java.messagestore.controller;

import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.service.MessageCRUD;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PageableAsQueryParam
    public Page<MessageResponse> findAll(@Parameter(hidden = true) Pageable pageable) {
        return messageCRUD.findAll(pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse get(@PathVariable UUID id) {
        return messageCRUD.getById(id)
                .orElseThrow(() -> messageNotFound(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody SaveMessageRequest request, UriComponentsBuilder ucb) {
        MessageResponse response = messageCRUD.create(request);
        return ResponseEntity
                .created(ucb.path("/messages/{id}").build(response.id()))
                .body(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable UUID id, @Valid @RequestBody SaveMessageRequest request) {
        messageCRUD.update(id, request);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse delete(@PathVariable UUID id) {
        return messageCRUD.deleteById(id)
                .orElseThrow(() -> messageNotFound(id));
    }

}
