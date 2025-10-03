package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.turma.CriarRequisicaoTurmaDto;
import com.example.gestaoEscolar.dto.turma.CriarRespostaTurmaDto;
import com.example.gestaoEscolar.model.Turma;

public class TurmaMapper {
    public Turma paraEntidade(CriarRequisicaoTurmaDto requisicaoDto){
        return new Turma(requisicaoDto.cursoId(), requisicaoDto.professorId(), requisicaoDto.nome());
    }

    public Turma verificarUpdate(CriarRequisicaoTurmaDto requisicaoDto, Turma turma){
        if ((requisicaoDto.cursoId() != turma.getCursoId()) && requisicaoDto.cursoId() != 0){
            turma.setCursoId(requisicaoDto.cursoId());
        }

        if ((requisicaoDto.professorId() != turma.getProfessorId()) && requisicaoDto.professorId() != 0){
            turma.setProfessorId(requisicaoDto.professorId());
        }

        if ((requisicaoDto.nome() != turma.getNome()) && requisicaoDto.nome() != null){
            turma.setNome(requisicaoDto.nome());
        }

        return turma;
    }

    public CriarRespostaTurmaDto paraResposta (Turma turma) {
        return new CriarRespostaTurmaDto(turma.getId(), turma.getCursoId(), turma.getProfessorId(), turma.getNome());
    }
}
