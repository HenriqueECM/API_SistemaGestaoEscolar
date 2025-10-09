package com.example.gestaoEscolar.model;

import java.time.LocalDateTime;

public class Aula {
    private int id, turma_id;
    private LocalDateTime dataHora;
    private String assunto, nomeTurma;

    public Aula(int id, int turma_id, String nomeTurma, LocalDateTime dataHora, String assunto) {
        this.id = id;
        this.turma_id = turma_id;
        this.dataHora = dataHora;
        this.assunto = assunto;
        this.nomeTurma = nomeTurma;
    }

    public Aula(int turma_id, LocalDateTime dataHora, String assunto) {
        this.turma_id = turma_id;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
