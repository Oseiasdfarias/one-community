package br.com.one_community.entities.question;

import jakarta.validation.constraints.NotNull;

public record QuestionDto(
        @NotNull(message = "Id do usuário é obrigatório.")
        Long userId,
        @NotNull(message = "Título é obrigatório.")
        String title,
        @NotNull(message = "Corpo do texto é obrigatório.")
        String body) {
}
