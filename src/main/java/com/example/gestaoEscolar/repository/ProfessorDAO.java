package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.Professor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorDAO {
    public Professor create (Professor professor) throws SQLException {
        String query = "INSERT INTO professor (nome, email, disciplina) VALUES (?,?,?)";

        try(Connection conn = Conexao.conexao();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                professor.setId(rs.getInt(1));
            }
        }
        return professor;
    }

    public List<Professor> buscarTodos() throws SQLException {
        List<Professor> professorList = new ArrayList<>();

        String query = "SELECT id, nome, email, disciplina FROM professor";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                var professor = new Professor(id, nome, email, disciplina);
                professorList.add(professor);
            }
        }
        return professorList;
    }

    public Professor buscarPorId(int id) throws SQLException {
        String query = "SELECT id, nome, email, disciplina FROM professor WHERE id = ?";

        int newId = 0;
        String nome = "";
        String email = "";
        String disciplina = "";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newId = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
                disciplina = rs.getString("disciplina");

                return new Professor(newId, nome, email, disciplina);
            }
        }
        return null;
    }

    public boolean verificarExistencia (int id) throws SQLException {
        String query = "SELECT id FROM professor WHERE id = ?";

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

    public void delete (int id) throws SQLException {
        String query = "DELETE FROM professor WHERE id = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }
    }

    public Professor update (Professor professor) throws SQLException {
        String query = "UPDATE professor SET nome = ?, email = ?, disciplina = ? WHERE id = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, professor.getId());

            stmt.executeUpdate();
        }
        return professor;
    }
}
