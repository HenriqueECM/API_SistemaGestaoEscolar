package com.example.gestaoEscolar.dto.response;

import java.util.List;

public record CriarRespostaCursoDto(
        int id,
        String nome,
        String codigo,
        List<String> listaProfessores
) {
}
