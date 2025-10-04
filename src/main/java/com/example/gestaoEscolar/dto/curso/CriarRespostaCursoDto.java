package com.example.gestaoEscolar.dto.curso;

import java.util.List;

public record CriarRespostaCursoDto(
        int id,
        String nome,
        String codigo
//        List<String> listaAlunos
) {
}
