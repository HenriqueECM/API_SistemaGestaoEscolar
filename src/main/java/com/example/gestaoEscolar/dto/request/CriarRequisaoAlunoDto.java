package com.example.gestaoEscolar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CriarRequisaoAlunoDto(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String nome,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
