package com.alevel.java.messagestore.model.message;

import javax.validation.constraints.NotBlank;

public record SaveMessageRequest(@NotBlank(message = "Title is mandatory") String title,
                                 @NotBlank(message = "Text content is mandatory") String text) {
}
