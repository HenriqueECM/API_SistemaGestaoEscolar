package com.example.gestaoEscolar.mapper;

import com.example.gestaoEscolar.dto.request.CriarRequisaoAlunoDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaAlunoDto;
import com.example.gestaoEscolar.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {
    public Aluno paraEntidade(CriarRequisaoAlunoDto requisaoAlunoDto){
        return new Aluno(requisaoAlunoDto.nome(), requisaoAlunoDto.email(), requisaoAlunoDto.matricula(), requisaoAlunoDto.dataNascimento());
    }

    public CriarRespostaAlunoDto paraResposta(Aluno aluno){
        return new CriarRespostaAlunoDto(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getDataNascimento());
    }

    public Aluno verificacaoUpdate(CriarRequisaoAlunoDto requisaoAlunoDto, Aluno aluno) {
        if ((requisaoAlunoDto.nome() != aluno.getNome()) && requisaoAlunoDto.nome() != null){
            aluno.setNome(requisaoAlunoDto.nome());
        }

        if ((requisaoAlunoDto.email() != aluno.getEmail()) && requisaoAlunoDto.email() != null){
            aluno.setEmail(requisaoAlunoDto.email());
        }

        if ((requisaoAlunoDto.matricula() != aluno.getMatricula()) && requisaoAlunoDto.matricula() != null){
            aluno.setMatricula(requisaoAlunoDto.matricula());
        }

        if ((requisaoAlunoDto.dataNascimento() != aluno.getDataNascimento()) && requisaoAlunoDto.dataNascimento() != null){
            aluno.setDataNascimento(requisaoAlunoDto.dataNascimento());
        }

        return aluno;
    }
}
