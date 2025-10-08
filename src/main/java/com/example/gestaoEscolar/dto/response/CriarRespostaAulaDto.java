package com.example.gestaoEscolar.dto.response;

import java.time.LocalDateTime;

public record CriarRespostaAulaDto(int id, int turma_id, LocalDateTime dataHora, String assunto) {
}
