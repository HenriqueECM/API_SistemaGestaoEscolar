package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.professor.CriarRequisicaoProfessorDto;
import com.example.gestaoEscolar.dto.professor.CriarRespostaProfessorDto;
import com.example.gestaoEscolar.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessroMapper {
    public Professor paraEntidade(CriarRequisicaoProfessorDto requisicaoProfessorDto){
        return new Professor(requisicaoProfessorDto.nome(), requisicaoProfessorDto.email(), requisicaoProfessorDto.disciplina());
    }

    public Professor verificarUpdate(CriarRequisicaoProfessorDto requisicaoProfessorDto, Professor professor){
        if ((requisicaoProfessorDto.nome() != professor.getNome()) && requisicaoProfessorDto.nome() != null){
            professor.setNome(requisicaoProfessorDto.nome());
        }

        if ((requisicaoProfessorDto.email() != professor.getEmail()) && requisicaoProfessorDto.email() != null){
            professor.setEmail(requisicaoProfessorDto.email());
        }

        if ((requisicaoProfessorDto.disciplina() != professor.getDisciplina()) && requisicaoProfessorDto.disciplina() != null){
            professor.setNome(requisicaoProfessorDto.disciplina());
        }

        return professor;
    }

    public CriarRespostaProfessorDto paraResposta (Professor professor) {
        return new CriarRespostaProfessorDto(professor.getId(), professor.getNome(), professor.getEmail(), professor.getDisciplina());
    }
}
