package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoAulaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaAulaDto;
import com.example.gestaoEscolar.model.Aula;

public class AulaMapper {
    public Aula paraEntidade(CriarRequisicaoAulaDto requisicaoAulaDto){
        return new Aula(requisicaoAulaDto.turma_id(), requisicaoAulaDto.dataHora(), requisicaoAulaDto.assunto());
    }

    public Aula verificarUpdate (CriarRequisicaoAulaDto requisicaoAulaDto, Aula aula){
        if ((requisicaoAulaDto.turma_id() != aula.getTurma_id()) && requisicaoAulaDto.turma_id() != 0){
            aula.setTurma_id(requisicaoAulaDto.turma_id());
        }

        if ((requisicaoAulaDto.dataHora() != aula.getDataHora()) && requisicaoAulaDto.dataHora() != null){
            aula.setDataHora(requisicaoAulaDto.dataHora());
        }

        if ((requisicaoAulaDto.assunto() != aula.getAssunto()) && requisicaoAulaDto.assunto() != null){
            aula.setAssunto(requisicaoAulaDto.assunto());
        }

        return aula;
    }

    public CriarRespostaAulaDto paraResposta(Aula aula){
        return new CriarRespostaAulaDto(aula.getId(), aula.getTurma_id(), aula.getDataHora(), aula.getAssunto());
    }
}
