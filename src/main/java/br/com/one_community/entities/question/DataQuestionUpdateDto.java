package br.com.one_community.entities.question;

import jakarta.validation.constraints.NotNull;

public record DataQuestionUpdateDto(
        @NotNull
        Long id,
        String title,
        String body) {
}
