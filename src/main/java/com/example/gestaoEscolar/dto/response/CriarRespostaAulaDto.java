package com.example.gestaoEscolar.dto.response;

import java.time.LocalDateTime;

public record CriarRespostaAulaDto(int id, String nomeTurma, LocalDateTime dataHora, String assunto) {
}
