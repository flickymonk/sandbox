package com.alevel.java.messagestore.controller;

import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.service.MessageCRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MessageControllerTest {

    private MockMvc mvc;

    private MessageCRUD messageCRUD;

    @BeforeEach
    void setUp() {
        messageCRUD = mock(MessageCRUD.class);
        mvc = MockMvcBuilders
                .standaloneSetup(new MessageController(messageCRUD))
                .build();
    }

    @Test
    void testGetMessageById() throws Exception {
        var id = UUID.randomUUID();
        var response = new MessageResponse(id, "title", "text");

        when(messageCRUD.getById(id)).thenReturn(Optional.of(response));

        var expectedJson = "{\"id\":\"" + id + "\",\"title\":\"title\",\"text\":\"text\"}";

        mvc.perform(get("/messages/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        verify(messageCRUD, only()).getById(id);
    }


    @Test
    void testGetMessageByAbsentId() throws Exception {
        var id = UUID.randomUUID();

        when(messageCRUD.getById(id)).thenReturn(Optional.empty());

        mvc.perform(get("/messages/" + id))
                .andExpect(status().isNotFound());

        verify(messageCRUD, only()).getById(id);
    }

    @Test
    void testCreateMessage() throws Exception {
        var request = new SaveMessageRequest("title", "text");
        var id = UUID.randomUUID();
        var response = new MessageResponse(id, "title", "text");

        when(messageCRUD.create(request)).thenReturn(response);

        var expectedJson = "{\"id\":\"" + id + "\",\"title\":\"title\",\"text\":\"text\"}";

        mvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"title\",\"text\":\"text\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        verify(messageCRUD, only()).create(request);
    }

    @Test
    void testUpdateMessage() throws Exception {
        var request = new SaveMessageRequest("title", "text");
        var id = UUID.randomUUID();

        mvc.perform(put("/messages/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"title\",\"text\":\"text\"}"))
                .andExpect(status().isNoContent());

        verify(messageCRUD, only()).update(id, request);
    }

    @Test
    void testDeleteMessage() throws Exception {
        var id = UUID.randomUUID();
        var response = new MessageResponse(id, "title", "text");
        var expectedJson = "{\"id\":\"" + id + "\",\"title\":\"title\",\"text\":\"text\"}";

        when(messageCRUD.deleteById(id))
                .thenReturn(Optional.of(response))
                .thenReturn(Optional.empty());

        mvc.perform(delete("/messages/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        mvc.perform(delete("/messages/" + id))
                .andExpect(status().isNotFound());

        verify(messageCRUD, times(2)).deleteById(id);
        verifyNoMoreInteractions(messageCRUD);
    }
}