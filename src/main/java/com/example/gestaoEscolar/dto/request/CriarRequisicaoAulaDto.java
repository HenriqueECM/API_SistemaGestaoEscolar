package com.example.gestaoEscolar.dto.request;

import java.time.LocalDateTime;

public record CriarRequisicaoAulaDto(int turma_id, LocalDateTime dataHora, String assunto) {
}
