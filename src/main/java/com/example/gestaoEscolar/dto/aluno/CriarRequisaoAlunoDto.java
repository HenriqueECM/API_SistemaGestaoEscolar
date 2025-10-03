package com.example.gestaoEscolar.dto.aluno;

import java.time.LocalDate;

public record CriarRequisaoAlunoDto(String nome, String email, String matricula, LocalDate dataNascimento) {
}
