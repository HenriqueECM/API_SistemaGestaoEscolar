package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.Curso;
import com.example.gestaoEscolar.util.GerarIn;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDAO {
    public Curso create(Curso curso) throws SQLException {
        String query = "INSERT INTO curso (nome, codigo) VALUES (?,?)";

        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                curso.setId(rs.getInt(1));
            }
        }
        return curso;
    }

    public List<Curso> buscarTodos() throws SQLException {
        List<Curso> cursoList = new ArrayList<>();
        String query = "SELECT id, nome, codigo FROM curso";

        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                var curso = new Curso(id, nome, codigo);
                cursoList.add(curso);
            }
        }
        return cursoList;
    }

    public Curso buscarPorId(int id) throws SQLException {
        String query = "SELECT id, nome, codigo FROM curso WHERE id = ?";

        int newId = 0;
        String nome = "";
        String codigo = "";

        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                newId = rs.getInt("id");
                nome = rs.getString("nome");
                codigo = rs.getString("codigo");

                return new Curso(newId, nome, codigo);
            }
        }
        return null;
    }

    public Curso update(Curso curso) throws SQLException {
        String query = "UPDATE curso SET nome = ?, codigo = ? WHERE id = ?";

        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setInt(3, curso.getId());

            stmt.executeUpdate();
        }
        return curso;
    }

    public boolean verificarExistencia(int id) throws SQLException {
        String query = "SELECT id FROM curso WHERE id = ?";
        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = Conexao.conexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }
    }

    // lista de professores que estão associados ao curso
    public List<String> listaProfessoresNome(List<Integer> idsProfessores) throws SQLException {
        String query = """
                SELECT p.nome
                FROM professor p
                LEFT JOIN turma t
                ON p.id = t.professor_id
                WHERE p.id IN""" + GerarIn.gerando(idsProfessores.size());

        List<String> nomeProfessores = new ArrayList<>();

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
        
            String nome = "";

            for (int i = 0; i < idsProfessores.size(); i++){
                stmt.setInt(i + 1, idsProfessores.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                nome = rs.getString("nome");
                nomeProfessores.add(nome);
            }
        }
        return nomeProfessores;
    }
}
