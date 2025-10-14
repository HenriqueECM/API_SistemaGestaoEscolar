package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoTurmaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaTurmaDto;
import com.example.gestaoEscolar.mapper.TurmaMapper;
import com.example.gestaoEscolar.model.Turma;
import com.example.gestaoEscolar.model.TurmaResposta;
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
        Turma turma = mapper.paraEntidade(requisitaoDto);

        Turma newTurma = repository.create(turma);

        repository.inserirAlunosTurma(newTurma.getId(), requisitaoDto.listaAlunoIds());

        List<String> nomeAluno = repository.buscarListaNomesPorId(requisitaoDto.listaAlunoIds());

        TurmaResposta turmaResposta = repository.buscarTurmasPorId(newTurma.getId());

        return mapper.paraResposta(turmaResposta, nomeAluno);
    }

    public List<CriarRespostaTurmaDto> buscarTodos() throws SQLException {
        List<TurmaResposta> turmaList = repository.buscarTurmas();
        List<CriarRespostaTurmaDto> respostaTurmaDtos = new ArrayList<>();

        for (TurmaResposta turma : turmaList){
            List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(turma.getId());
            respostaTurmaDtos.add(mapper.paraResposta(turma, nomeAlunos));
        }
        return respostaTurmaDtos;
    }

    public CriarRespostaTurmaDto buscarPorId(int id) throws SQLException {
        TurmaResposta turma = repository.buscarTurmasPorId(id);

        if (turma == null){
            throw new RuntimeException("Turma ID " + id + " não encontrado");
        }

        List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(turma.getId());

        return mapper.paraResposta(turma, nomeAlunos);
    }

//    public CriarRespostaTurmaDto update (int id, CriarRequisicaoTurmaDto requisitaoDto) throws SQLException {
//        Turma turma = repository.buscarPorId(id);
//
//        if (turma == null){
//            throw new RuntimeException("Turma ID " + id + " não encontrado");
//        }
//
//        return mapper.paraResposta(repository.update(mapper.verificarUpdate(requisitaoDto, turma)));
//    }

    public void delete (int id) throws SQLException {
        if (!repository.verificarExistencia(id)){
            throw new RuntimeException("Turma ID " + id + " não encontrado");
        }
        repository.delete(id);
    }
}