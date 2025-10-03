package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.aluno.CriarRequisaoAlunoDto;
import com.example.gestaoEscolar.dto.aluno.CriarRespostaAlunoDto;
import com.example.gestaoEscolar.mapper.AlunoMapper;
import com.example.gestaoEscolar.model.Aluno;
import com.example.gestaoEscolar.repository.AlunoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    private final AlunoDAO repository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoDAO repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriarRespostaAlunoDto create (CriarRequisaoAlunoDto requisaoAlunoDto) throws SQLException {
        if (!repository.verificarExistencia(requisaoAlunoDto.email())){
            throw new RuntimeException("O Aluno já existe!");
        }

        return mapper.paraResposta(repository.create(mapper.paraEntidade(requisaoAlunoDto)));
    }

    public List<CriarRespostaAlunoDto> buscarTodos() throws SQLException {
        List<Aluno> alunoList = repository.buscarTodos();
        List<CriarRespostaAlunoDto> respostaAlunoDtos = new ArrayList<>();

        alunoList.forEach(aluno -> {
            respostaAlunoDtos.add(mapper.paraResposta(aluno));
        });

        return respostaAlunoDtos;
    }

    public CriarRespostaAlunoDto buscarPorId(int id) throws SQLException {
        Aluno aluno = repository.buscarPorId(id);

        if (aluno == null){
            throw new RuntimeException("Aluno ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(aluno);
    }

    public CriarRespostaAlunoDto update (int id, CriarRequisaoAlunoDto requisaoAlunoDto) throws SQLException {
        Aluno aluno = repository.buscarPorId(id);

        if (aluno == null){
            throw new RuntimeException("Aluno ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(repository.update(mapper.verificacaoUpdate(requisaoAlunoDto, aluno)));
    }

    public void delete (int id) throws SQLException {
        if(!repository.verificarExistenciaId(id)){
            throw new RuntimeException("Aluno ID " + id + " não encontrado!");
        }

        repository.delete(id);
    }
}