package com.alevel.java.messagestore;

import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import com.alevel.java.messagestore.model.message.MessageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class MessageStoreApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    void testContextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    void testCreateMessage() {
        var title = "test message";
        var text = "test message content";
        ResponseEntity<MessageResponse> messageResponseEntity = createMessage(title, text);

        assertEquals(HttpStatus.CREATED, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testCreateInvalidMessage() {
        ResponseEntity<?> blankTitleResponse = createMessage("", "text");
        assertEquals(HttpStatus.BAD_REQUEST, blankTitleResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, blankTitleResponse.getHeaders().getContentType());

        ResponseEntity<?> blankTextResponse = createMessage("title", "   ");
        assertEquals(HttpStatus.BAD_REQUEST, blankTextResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, blankTextResponse.getHeaders().getContentType());

        ResponseEntity<?> noTitleAndTextResponse = createMessage(null, null);
        assertEquals(HttpStatus.BAD_REQUEST, noTitleAndTextResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, noTitleAndTextResponse.getHeaders().getContentType());
    }

    @Test
    void testGetMessageById() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.getId();

        var messageUrl = baseUrl(id);

        ResponseEntity<MessageResponse> messageResponseEntity = rest
                .getForEntity(messageUrl, MessageResponse.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertEquals(id, responseBody.getId());

        assertEquals(responseBody, rest.getForEntity(messageUrl, MessageResponse.class).getBody());
    }

    @Test
    void testGetNonExistingMessage() {
        var messageUrl = baseUrl(UUID.randomUUID());

        ResponseEntity<MessageResponse> messageResponseEntity = rest.getForEntity(messageUrl, MessageResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, messageResponseEntity.getStatusCode());
    }

    @Test
    void testUpdateMessage() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.getId();

        var messageUrl = baseUrl(id);

        var updatedTitle = "updated message title";
        var updatedText = "updated message text";

        rest.put(messageUrl, new SaveMessageRequest(updatedTitle, updatedText));

        ResponseEntity<MessageResponse> messageResponseEntity = rest.getForEntity(messageUrl, MessageResponse.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
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

        var messageUrl = baseUrl(id);
        var messageUri = URI.create(messageUrl);

        ResponseEntity<MessageResponse> messageResponseEntity = rest
                .exchange(RequestEntity.delete(messageUri).build(), MessageResponse.class);

        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.getTitle());
        assertEquals(text, responseBody.getText());
        assertEquals(id, responseBody.getId());

        assertEquals(HttpStatus.NOT_FOUND, rest.getForEntity(messageUrl, MessageResponse.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(messageUri).build(), MessageResponse.class)
                .getStatusCode());
    }

    private ResponseEntity<MessageResponse> createMessage(String title, String text) {
        var url = baseUrl();
        var requestBody = new SaveMessageRequest();
        requestBody.setTitle(title);
        requestBody.setText(text);

        return rest.postForEntity(url, requestBody, MessageResponse.class);
    }

    private String baseUrl() {
        return "http://localhost:" + port + "/messages";
    }

    private String baseUrl(UUID id) {
        return baseUrl() + '/' + id;
    }
}
