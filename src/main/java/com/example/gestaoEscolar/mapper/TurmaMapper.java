package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoTurmaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaTurmaDto;
import com.example.gestaoEscolar.model.Turma;
import com.example.gestaoEscolar.model.TurmaResposta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {
    public Turma paraEntidade(CriarRequisicaoTurmaDto requisicaoDto){
        return new Turma(requisicaoDto.nome(), requisicaoDto.cursoId(), requisicaoDto.professorId());
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

    public CriarRespostaTurmaDto paraResposta(TurmaResposta turma, List<String> nomeAlunos) {
        return new CriarRespostaTurmaDto(turma.getId(), turma.getNome(), turma.getNomeCurso(), turma.getNomeProfessor(), nomeAlunos);
    }
}
