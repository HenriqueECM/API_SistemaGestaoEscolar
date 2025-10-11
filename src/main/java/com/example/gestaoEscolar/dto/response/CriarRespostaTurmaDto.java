package com.example.gestaoEscolar.dto.response;

import java.util.List;

public record CriarRespostaTurmaDto(
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> nomeAlunos
) {
}
