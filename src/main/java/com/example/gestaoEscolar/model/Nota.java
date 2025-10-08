package com.example.gestaoEscolar.model;

public class Nota {
    private int id, alunoId, aulaId;
    private double valor;
    
    public Nota(int alunoId, int aulaId, double valor) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }

    public Nota(int id, int alunoId, int aulaId, double valor) {
        this.id = id;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAlunoId() {
        return alunoId;
    }
    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
    public int getAulaId() {
        return aulaId;
    }
    public void setAulaId(int aulaId) {
        this.aulaId = aulaId;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }


}
