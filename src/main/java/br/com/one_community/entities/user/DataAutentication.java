package br.com.one_community.entities.user;

import jakarta.validation.constraints.NotBlank;

public record DataAutentication(
        @NotBlank(message = "Campo Login é obrigatório.")
        String email,
        @NotBlank(message = "Campo Senha é obrigatório.")
        String senha) {
}
