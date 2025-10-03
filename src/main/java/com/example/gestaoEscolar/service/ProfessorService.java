package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.professor.CriarRequisicaoProfessorDto;
import com.example.gestaoEscolar.dto.professor.CriarRespostaProfessorDto;
import com.example.gestaoEscolar.mapper.ProfessroMapper;
import com.example.gestaoEscolar.model.Professor;
import com.example.gestaoEscolar.repository.ProfessorDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorDAO repository;
    private final ProfessroMapper mapper;

    public ProfessorService(ProfessorDAO repository, ProfessroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriarRespostaProfessorDto create (CriarRequisicaoProfessorDto requisicaoProfessorDto) throws SQLException {
        return mapper.paraResposta(repository.create(mapper.paraEntidade(requisicaoProfessorDto)));
    }

    public List<CriarRespostaProfessorDto> buscarTodos() throws SQLException {
        List<Professor> professorList = repository.buscarTodos();
        List<CriarRespostaProfessorDto> respostaProfessorDtos = new ArrayList<>();

        professorList.forEach(professor -> {
            respostaProfessorDtos.add(mapper.paraResposta(professor));
        });
        return respostaProfessorDtos;
    }

    public CriarRespostaProfessorDto buscarPorId(int id) throws SQLException {
        Professor professor = repository.buscarPorId(id);

        if (professor == null){
            throw new RuntimeException("Professor ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(professor);
    }

    public CriarRespostaProfessorDto update (int id, CriarRequisicaoProfessorDto requisicaoProfessorDto) throws SQLException {
        Professor professor = repository.buscarPorId(id);

        if (professor == null){
            throw new RuntimeException("Professor ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(repository.update(mapper.verificarUpdate(requisicaoProfessorDto, professor)));
    }

    public void delete (int id) throws SQLException {
        if (!repository.verificarExistencia(id)){
            throw new RuntimeException("Professor ID " + id + " não encontrado!");
        }

        repository.delete(id);
    }
}
