package com.example.gestaoEscolar.dto.response;

public record NotaResponse(
        int id,
        int alunoId,
        int aulaId,
        double valor
) {
}
