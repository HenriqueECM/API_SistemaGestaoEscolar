package com.example.gestaoEscolar.dto.request;

public record CriarRequisicaoTurmaDto(
        int cursoId,
        int professorId,
        String nome
) {
}
