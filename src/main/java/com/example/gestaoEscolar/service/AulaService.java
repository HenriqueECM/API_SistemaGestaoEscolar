package com.example.gestaoEscolar.service;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoAulaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaAulaDto;
import com.example.gestaoEscolar.mapper.AulaMapper;
import com.example.gestaoEscolar.model.Aula;
import com.example.gestaoEscolar.repository.AulaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AulaService {
    private final AulaMapper mapper;
    private final AulaDAO repository;

    public AulaService(AulaMapper mapper, AulaDAO repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CriarRespostaAulaDto create (CriarRequisicaoAulaDto requisicaoAulaDto) throws SQLException {
        return mapper.paraResposta(repository.create(mapper.paraEntidade(requisicaoAulaDto)));
    }

    public CriarRespostaAulaDto update (int id, CriarRequisicaoAulaDto requisicaoAulaDto) throws SQLException {
        Aula aula = repository.buscarPorId(id);

        if (aula == null){
            throw new RuntimeException("Aula ID " + id + " não encontrado!");
        }
        return mapper.paraResposta(repository.update(mapper.verificarUpdate(requisicaoAulaDto, aula)));
    }

    public List<CriarRespostaAulaDto> buscarTodos () throws SQLException {
        List<CriarRespostaAulaDto> respostaAulaDtos = new ArrayList<>();
        List<Aula> aulaList = repository.buscarTodos();

        aulaList.forEach(aula -> {
            respostaAulaDtos.add(mapper.paraResposta(aula));
        });
        return respostaAulaDtos;
    }

    public CriarRespostaAulaDto buscarPorId(int id) throws SQLException {
        Aula aula = repository.buscarPorId(id);

        if (aula == null){
            throw new RuntimeException("Aula ID " + id + " não encontrado!");
        }
        return mapper.paraResposta(aula);
    }

    public void delete (int id) throws SQLException {
        if (!repository.verificarExistencia(id)){
            throw new RuntimeException("Aula ID " + id + " mão encontrado");
        }
        repository.delete(id);
    }
}
