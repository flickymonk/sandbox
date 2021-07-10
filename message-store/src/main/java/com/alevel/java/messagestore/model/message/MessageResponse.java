package com.alevel.java.messagestore.model.message;

import java.util.UUID;

public record MessageResponse(UUID id, String title, String text) {

    public static MessageResponse fromMessage(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getTitle(),
                message.getText()
        );
    }

}
