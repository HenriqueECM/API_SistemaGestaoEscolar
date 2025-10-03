package com.example.gestaoEscolar.dto.aluno;

import java.time.LocalDate;

public record CriarRespostaAlunoDto(int id, String nome, String email, String matricula, LocalDate dataNascimento) {
}
