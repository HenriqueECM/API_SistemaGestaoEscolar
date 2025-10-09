package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.request.NotaResquest;
import com.example.gestaoEscolar.dto.response.NotaResponse;
import com.example.gestaoEscolar.model.Aluno;
import com.example.gestaoEscolar.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {
    public Nota paraEntidade(NotaResquest resquest) {
        return new Nota(resquest.alunoId(), resquest.aulaId(), resquest.valor());
    }

    public NotaResponse paraResposta(Nota nota, Aluno aluno) {
        return new NotaResponse(nota.getId(), nota.getAlunoId(), nota.getAulaId(), nota.getValor());
    }

    public Nota verificarUpdate (NotaResquest resquest, Nota nota) {
        if ((resquest.alunoId() != nota.getAlunoId()) && resquest.alunoId() != 0){
            nota.setAlunoId(resquest.alunoId());
        }

        if ((resquest.aulaId() != nota.getAulaId()) && resquest.aulaId() != 0){
            nota.setAulaId(resquest.aulaId());
        }

        if ((resquest.valor() != nota.getValor()) && resquest.valor() != 0){
            nota.setValor(resquest.valor());
        }

        return nota;
    }
}
