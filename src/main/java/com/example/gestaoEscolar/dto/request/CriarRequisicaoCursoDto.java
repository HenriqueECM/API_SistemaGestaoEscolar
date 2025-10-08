package com.example.gestaoEscolar.dto.request;

import java.util.List;

public record CriarRequisicaoCursoDto(
        String nome,
        String codigo,
        List<Integer> listaProfessorIds
) {
}
