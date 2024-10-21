package br.com.one_community.entities.user;


import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public record UserDto(
        @NotBlank(message = "Nome é obrigatório.")
        @Column(name = "username")
        String userName,
        @NotNull(message = "Email é obrigatório.")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotNull(message = "Senha é obrigatório.")
        String password,
        @NotNull(message = "Tipo de usuário obrigatório [Admin, Regular].")
        @Valid
        Role role) {
}
