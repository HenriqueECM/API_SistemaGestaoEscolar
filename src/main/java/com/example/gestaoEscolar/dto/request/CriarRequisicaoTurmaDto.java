package com.example.gestaoEscolar.dto.request;

import java.util.List;

public record CriarRequisicaoTurmaDto(
        int cursoId,
        int professorId,
        String nome,
        List<Integer>listaAlunoIds
) {
}
