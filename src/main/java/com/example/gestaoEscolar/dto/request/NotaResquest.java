package com.example.gestaoEscolar.dto.request;

public record NotaResquest(
        int alunoId,
        int aulaId,
        double valor
) {
}
