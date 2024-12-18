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
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=." +
                        "*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, incluindo uma " +
                        "letra maiúscula, uma letra minúscula, um número e um caractere " +
                        "especial.")
        String password,
        @NotNull(message = "Tipo de usuário obrigatório [Admin, Regular].")
        @Valid
        Role role) {
}
