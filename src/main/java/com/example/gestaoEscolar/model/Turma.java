package com.example.gestaoEscolar.model;

public class Turma {
    private int id, cursoId, professorId;
    private String nome;

    public Turma(int id,String nome, int cursoId, int professorId) {
        this.id = id;
        this.cursoId = cursoId;
        this.professorId = professorId;
        this.nome = nome;
    }

    public Turma(String nome, int cursoId, int professorId) {
        this.cursoId = cursoId;
        this.professorId = professorId;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
