package com.alevel.java.messagestore.service;

import com.alevel.java.messagestore.model.message.Message;
import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class MessageServiceTest {

    private MessageService messageService;

    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = mock(MessageRepository.class);
        messageService = new MessageService(messageRepository);
    }

    @Test
    void testGetMessageById() {
        var absentId = UUID.randomUUID();
        var presentId = UUID.randomUUID();
        var message = new Message(presentId, "title", "text");

        when(messageRepository.findById(absentId)).thenReturn(Optional.empty());
        when(messageRepository.findById(presentId)).thenReturn(Optional.of(message));

        Optional<MessageResponse> absentResponse = messageService.getById(absentId);

        assertThat(absentResponse).isEmpty();
        verify(messageRepository).findById(absentId);

        Optional<MessageResponse> presentResponse = messageService.getById(presentId);

        assertThat(presentResponse).hasValueSatisfying(messageResponse ->
                assertMessageMatchesResponse(message, messageResponse));
        verify(messageRepository).findById(presentId);

        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void testUpdateMessage() {
        var presentId = UUID.randomUUID();
        var absentId = UUID.randomUUID();
        var update = new SaveMessageRequest("new title", "new text");

        var message = new Message(presentId, "title", "text");

        when(messageRepository.findById(absentId)).thenReturn(Optional.empty());
        when(messageRepository.findById(presentId)).thenReturn(Optional.of(message));
        when(messageRepository.save(same(message))).thenReturn(message);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> messageService.update(absentId, update))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.NOT_FOUND));

        verify(messageRepository).findById(absentId);

        messageService.update(presentId, update);

        assertThat(message.getTitle()).isEqualTo(update.getTitle());
        assertThat(message.getText()).isEqualTo(update.getText());
        verify(messageRepository).findById(presentId);
        verify(messageRepository).save(same(message));

        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void testDeleteMessage() {
        var absentId = UUID.randomUUID();
        var presentId = UUID.randomUUID();
        var message = new Message(presentId, "title", "text");

        when(messageRepository.findById(absentId)).thenReturn(Optional.empty());
        when(messageRepository.findById(presentId)).thenReturn(Optional.of(message));

        Optional<MessageResponse> absentResponse = messageService.deleteById(absentId);

        assertThat(absentResponse).isEmpty();
        verify(messageRepository).findById(absentId);

        Optional<MessageResponse> presentResponse = messageService.deleteById(presentId);

        assertThat(presentResponse).hasValueSatisfying(messageResponse ->
                assertMessageMatchesResponse(message, messageResponse));
        verify(messageRepository).findById(presentId);
        verify(messageRepository).delete(message);

        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void testCreateMessage() {
        var request = new SaveMessageRequest("title", "text");
        var messageId = UUID.randomUUID();

        when(messageRepository.save(notNull())).thenAnswer(invocation -> {
            Message entity = invocation.getArgument(0);
            assertThat(entity.getId()).isNull();
            assertThat(entity.getTitle()).isEqualTo(request.getTitle());
            assertThat(entity.getText()).isEqualTo(request.getText());
            entity.setId(messageId);
            return entity;
        });

        MessageResponse response = messageService.create(request);

        assertThat(response.getId()).isEqualTo(messageId);
        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getText()).isEqualTo(request.getText());
        verify(messageRepository, only()).save(notNull());
    }

    private static void assertMessageMatchesResponse(Message message, MessageResponse messageResponse) {
        assertThat(messageResponse.getId()).isEqualTo(message.getId());
        assertThat(messageResponse.getTitle()).isEqualTo(message.getTitle());
        assertThat(messageResponse.getText()).isEqualTo(message.getText());
    }
}