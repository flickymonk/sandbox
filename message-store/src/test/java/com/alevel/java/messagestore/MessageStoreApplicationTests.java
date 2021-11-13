package com.alevel.java.messagestore;

import com.alevel.java.messagestore.model.message.MessageResponse;
import com.alevel.java.messagestore.model.message.SaveMessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"h2db", "debug"})
class MessageStoreApplicationTests {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void testContextLoads() {
        assertNotNull(rest);
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
        assertEquals(title, responseBody.title());
        assertEquals(text, responseBody.text());
        assertNotNull(responseBody.id());
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

        UUID id = message.id();

        var messageUrl = baseUrl(id);

        ResponseEntity<MessageResponse> messageResponseEntity = rest
                .getForEntity(messageUrl, MessageResponse.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.title());
        assertEquals(text, responseBody.text());
        assertEquals(id, responseBody.id());

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

        UUID id = message.id();

        var messageUrl = baseUrl(id);

        var updatedTitle = "updated message title";
        var updatedText = "updated message text";

        rest.put(messageUrl, new SaveMessageRequest(updatedTitle, updatedText));

        ResponseEntity<MessageResponse> messageResponseEntity = rest.getForEntity(messageUrl, MessageResponse.class);
        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(updatedTitle, responseBody.title());
        assertEquals(updatedText, responseBody.text());
        assertEquals(id, responseBody.id());
    }

    @Test
    void testDeleteMessage() {
        var title = "new message";
        var text = "message text";

        var message = createMessage(title, text).getBody();
        assertNotNull(message);

        UUID id = message.id();

        var messageUrl = baseUrl(id);

        ResponseEntity<MessageResponse> messageResponseEntity = rest
                .exchange(RequestEntity.delete(messageUrl).build(), MessageResponse.class);

        assertEquals(HttpStatus.OK, messageResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, messageResponseEntity.getHeaders().getContentType());

        MessageResponse responseBody = messageResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(title, responseBody.title());
        assertEquals(text, responseBody.text());
        assertEquals(id, responseBody.id());

        assertEquals(HttpStatus.NOT_FOUND, rest.getForEntity(messageUrl, MessageResponse.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(messageUrl).build(), MessageResponse.class)
                .getStatusCode());
    }

    private ResponseEntity<MessageResponse> createMessage(String title, String text) {
        var url = baseUrl();
        var requestBody = new SaveMessageRequest(title, text);

        return rest.postForEntity(url, requestBody, MessageResponse.class);
    }

    private URI baseUrl() {
        return URI.create("/messages");
    }

    private URI baseUrl(UUID id) {
        return new UriTemplate("/messages/{id}").expand(id);
    }
}
