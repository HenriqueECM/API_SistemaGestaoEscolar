package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.TurmaAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TurmaAlunoDAO {
    public TurmaAluno create (TurmaAluno turmaAluno) throws SQLException {
        String query = "INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (?,?)";

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, turmaAluno.getTurmaId());
            stmt.setInt(2, turmaAluno.getAlunoId());
            stmt.executeUpdate();
        }
        return turmaAluno;
    }
}
