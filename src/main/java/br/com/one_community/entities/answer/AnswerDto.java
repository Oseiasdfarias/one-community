package br.com.one_community.entities.answer;

import jakarta.validation.constraints.NotNull;

public record AnswerDto(
        @NotNull(message = "Id da pergunta é obrigatório.")
        Long questionId,
        @NotNull(message = "Corpo do texto é obrigatório.")
        String body) {
}
