package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoTurmaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaTurmaDto;
import com.example.gestaoEscolar.mapper.TurmaMapper;
import com.example.gestaoEscolar.model.Turma;
import com.example.gestaoEscolar.repository.TurmaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {
    private final TurmaMapper mapper;
    private final TurmaDAO repository;

    public TurmaService(TurmaMapper mapper, TurmaDAO repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CriarRespostaTurmaDto create (CriarRequisicaoTurmaDto requisitaoDto) throws SQLException {
        return mapper.paraResposta(repository.create(mapper.paraEntidade(requisitaoDto)));
    }

    public CriarRespostaTurmaDto update (int id, CriarRequisicaoTurmaDto requisitaoDto) throws SQLException {
        Turma turma = repository.buscarPorId(id);

        if (turma == null){
            throw new RuntimeException("Turma ID " + id + " não encontrado");
        }

        return mapper.paraResposta(repository.update(mapper.verificarUpdate(requisitaoDto, turma)));
    }

    public void delete (int id) throws SQLException {
        if (!repository.verificarExistencia(id)){
            throw new RuntimeException("Turma ID " + id + " não encontrado");
        }
        repository.delete(id);
    }

    public List<CriarRespostaTurmaDto> buscarTodos() throws SQLException {
        List<Turma> turmaList = repository.buscarTodos();
        List<CriarRespostaTurmaDto> respostaTurmaDtos = new ArrayList<>();

        turmaList.forEach(turma -> {
            respostaTurmaDtos.add(mapper.paraResposta(turma));
        });
        return respostaTurmaDtos;
    }

    public CriarRespostaTurmaDto buscarPorId(int id) throws SQLException {
        Turma turma = repository.buscarPorId(id);

        if (turma == null){
            throw new RuntimeException("Turma ID " + id + " não encontrado");
        }

        return mapper.paraResposta(turma);
    }

    public CriarRespostaTurmaDto buscarTurmasPorCurso(int idCurso) throws SQLException {
        Turma turma = repository.buscaTurmaPorCurso(idCurso);

        if (turma == null){
            throw new RuntimeException("Curso ID " + idCurso + " não encontrado");
        }

        return mapper.paraResposta(turma);
    }
}