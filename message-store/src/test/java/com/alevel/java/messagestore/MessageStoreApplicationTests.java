package com.alevel.java.messagestore;

import com.alevel.java.messagestore.entity.Message;
import com.alevel.java.messagestore.entity.request.SaveMessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageStoreApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    void contextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    void testCreateMessage() {
        var title = "test message";
        var text = "test message content";
        ResponseEntity<Message> messageResponseEntity = createMessage(title, text);

        assertEquals(HttpStatus.CREATED, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        Message responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetMessageById() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.getId();

        var messageUrl = "http://localhost:" + port + "/messages/" + id;

        ResponseEntity<Message> messageResponseEntity = rest
                .getForEntity(messageUrl, Message.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        Message responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertEquals(id, responseBody.getId());

        assertEquals(responseBody, rest.getForEntity(messageUrl, Message.class).getBody());
    }

    @Test
    void testGetNonExistingMessage() {
        var messageUrl = "http://localhost:" + port + "/messages/" + UUID.randomUUID();

        ResponseEntity<Message> messageResponseEntity = rest.getForEntity(messageUrl, Message.class);

        assertEquals(HttpStatus.NOT_FOUND, messageResponseEntity.getStatusCode());
    }

    @Test
    void testUpdateMessage() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.getId();

        var messageUrl = "http://localhost:" + port + "/messages/" + id;

        var updatedTitle = "updated message title";
        var updatedText = "updated message text";

        rest.put(messageUrl, new SaveMessageRequest(updatedTitle, updatedText));

        ResponseEntity<Message> messageResponseEntity = rest.getForEntity(messageUrl, Message.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        Message responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(updatedTitle, responseBody.getTitle());
        assertEquals(updatedText, responseBody.getText());
        assertEquals(id, responseBody.getId());
    }

    @Test
    void testDeleteMessage() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.getId();

        var messageUrl = "http://localhost:" + port + "/messages/" + id;
        var messageUri = URI.create(messageUrl);

        ResponseEntity<Message> messageResponseEntity = rest
                .exchange(RequestEntity.delete(messageUri).build(), Message.class);

        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        Message responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertEquals(id, responseBody.getId());

        assertEquals(HttpStatus.NOT_FOUND, rest.getForEntity(messageUrl, Message.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(messageUri).build(), Message.class)
                .getStatusCode());
    }

    private ResponseEntity<Message> createMessage(String title, String text) {
        var url = "http://localhost:" + port + "/messages";
        var requestBody = new SaveMessageRequest();
        requestBody.setTitle(title);
        requestBody.setText(text);

        return rest.postForEntity(url, requestBody, Message.class);
    }
}
