package com.example.gestaoEscolar.dto.request;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CriarRequisicaoAulaDto(
        @Positive(message = "O id deve ser positivo")
        int turma_id,
        LocalDateTime dataHora,
        String assunto) {
}
