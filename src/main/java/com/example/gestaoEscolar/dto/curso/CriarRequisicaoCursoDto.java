package com.example.gestaoEscolar.dto.curso;

import java.util.List;

public record CriarRequisicaoCursoDto(
        String nome,
        String codigo,
        List<Integer> listaProfessorIds
) {
}
