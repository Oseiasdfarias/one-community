package br.com.one_community.entities.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateUserDto(
        String userName,
        @Email(message = "Formato do email é inválido")
        String email,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=." +
                        "*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, incluindo uma " +
                        "letra maiúscula, uma letra minúscula, um número e um caractere " +
                        "especial.")
        @Column(nullable = true)
        String password) {
}
