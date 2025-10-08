package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoCursoDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaCursoDto;
import com.example.gestaoEscolar.mapper.CursoMapper;
import com.example.gestaoEscolar.model.Curso;
import com.example.gestaoEscolar.repository.CursoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    private final CursoDAO repository;
    private final CursoMapper mapper;

    public CursoService(CursoDAO repository, CursoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // METODO CREATE
    public CriarRespostaCursoDto create (CriarRequisicaoCursoDto requisicaoCursoDto) throws SQLException {
        List<String> nomeProfessores = repository.listaProfessoresNome(requisicaoCursoDto.listaProfessorIds());

        return mapper.paraResposta(repository.create(mapper.paraEntidade(requisicaoCursoDto)), nomeProfessores);
    }

    // METODO UPDATE
    public CriarRespostaCursoDto update (int id, CriarRequisicaoCursoDto requisicaoCursoDto) throws SQLException {
        Curso curso = repository.buscarPorId(id);

        if (curso == null){
            throw new RuntimeException("Curso ID " + id + " não encontrado");
        }

        return mapper.paraResposta(repository.update(mapper.verificarUpdate(requisicaoCursoDto, curso)), null);
    }

    // METODO DELETE
    public void delete (int id) throws SQLException {
        if (!repository.verificarExistencia(id)){
            throw new RuntimeException("Curso ID " + id + " não encontrado");
        }

        repository.delete(id);
    }

    // METODO BUSCAR POR ID
    public CriarRespostaCursoDto buscarPorId(int id) throws SQLException {
        Curso curso = repository.buscarPorId(id);

        if (curso == null) {
            throw new RuntimeException("Curso ID " + id + " não encontrado");
        }

        return mapper.paraResposta(curso, null);
    }

    // METODO BUSCAR TODOS
    public List<CriarRespostaCursoDto> buscarTodos() throws SQLException {
        List<Curso> cursoList = repository.buscarTodos();
        List<CriarRespostaCursoDto> respostaCursoDtos = new ArrayList<>();

        cursoList.forEach(curso -> {
            respostaCursoDtos.add(mapper.paraResposta(curso, null));
        });
        return respostaCursoDtos;
    }
}