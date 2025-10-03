package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.Turma;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaDAO {
    public Turma create (Turma turma) throws SQLException {
        String query = "INSERT INTO turma (nome, curso_id, professor_id) VALUES (?,?,?)";

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCursoId());
            stmt.setInt(3, turma.getProfessorId());

            stmt.executeUpdate();
        }
        return turma;
    }

    public Turma update (Turma turma) throws SQLException{
        String query = "UPDATE turma SET nome = ?, curso_id = ?, professor_id = ? WHERE id = ?";
        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCursoId());
            stmt.setInt(3, turma.getProfessorId());
            stmt.setInt(4, turma.getId());

            stmt.executeUpdate();
        }
        return turma;
    }

    public void delete (int id) throws SQLException{
        String query = "DELETE FROM turma WHERE id = ?";
        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public boolean verificarExistencia(int id) throws SQLException{
        String query = "SELECT id FROM turma WHERE id = ?";
        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    public List<Turma> buscarTodos() throws SQLException {
        List<Turma> turmaList = new ArrayList<>();
        String query = "SELECT id, nome, curso_id, professor_id FROM turma";
        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");

                var turma = new Turma(id, cursoId, professorId, nome);
                turmaList.add(turma);
            }
        }
        return turmaList;
    }

    public Turma buscarPorId(int id) throws SQLException{
        String query = "SELECT id, nome, curso_id, professor_id FROM turma WHERE id = ?";
        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");

                return new Turma(newId, cursoId, professorId, nome);
            }
        }
        return null;
    }
}