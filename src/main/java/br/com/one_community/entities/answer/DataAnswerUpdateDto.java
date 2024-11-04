package br.com.one_community.entities.answer;

import jakarta.validation.constraints.NotNull;

public record DataAnswerUpdateDto(
        @NotNull(message = "Id da resposta é obrigatório.")
        Long answerId,
        @NotNull(message = "Corpo do texto é obrigatório.")
        String body) {
}
