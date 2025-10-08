package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.curso.CriarRequisicaoCursoDto;
import com.example.gestaoEscolar.dto.curso.CriarRespostaCursoDto;
import com.example.gestaoEscolar.model.Curso;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public Curso paraEntidade(CriarRequisicaoCursoDto requisicaoDto){
        return new Curso(requisicaoDto.nome(), requisicaoDto.codigo());
    }

    public Curso verificarUpdate(CriarRequisicaoCursoDto requisicaoDto, Curso curso){
        if ((requisicaoDto.nome() != curso.getNome()) && curso.getNome() != null){
            curso.setNome(requisicaoDto.nome());
        }

        if ((requisicaoDto.codigo() != curso.getCodigo()) && requisicaoDto.codigo() != null){
            curso.setCodigo(requisicaoDto.codigo());
        }

        return curso;
    }

    public CriarRespostaCursoDto paraResposta(Curso curso, List<String> professores){
        return new CriarRespostaCursoDto(curso.getId(), curso.getNome(), curso.getCodigo(), professores);
    }
}
