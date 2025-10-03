package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    // POST
    public Aluno create (Aluno aluno) throws SQLException {
        String query = "INSERT INTO aluno (nome, email, matricula, data_nascimento) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conexao();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.NO_GENERATED_KEYS)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                aluno.setId(rs.getInt(1));
            }

            System.out.println("Aluno criado com sucesso!");
        }
        return aluno;
    }

    // GET
    public List<Aluno> buscarTodos() throws SQLException {
        String query = "SELECT id, nome, email, matricula, data_nascimento FROM aluno";

        List<Aluno> alunoList = new ArrayList<>();

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate dataNascimento = rs.getDate("data_nascimentp").toLocalDate();

                var aluno = new Aluno(id, nome, email, matricula, dataNascimento);
                alunoList.add(aluno);
            }
        }
        return alunoList;
    }

    // GET BY ID
    public Aluno buscarPorId(int id) throws SQLException {
        String query = "SELECT id, nome, email, matricula, data_nascimento FROM aluno WHERE id = ?";

        int NewId = 0;
        String nome = "";
        String email = "";
        String matricula = "";
        LocalDate dataNascimento = null;

        try(Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                NewId = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
                matricula = rs.getString("matricula");
                dataNascimento = rs.getDate("data_nascimento").toLocalDate();
            }
        }
        return new Aluno(NewId, nome, email, matricula, dataNascimento);
    }

    // VERIFICAR EXISTENCIA POR EMAIL
    public boolean verificarExistencia(String email) throws SQLException{
        String query = "SELECT id FROM aluno WHERE email = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    // VERIFICAR EXISTENCIA
    public boolean verificarExistenciaId(int id) throws SQLException{
        String query = "SELECT id FROM aluno WHERE id = ?";

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

    // PUT
    public Aluno update (Aluno aluno) throws SQLException {
        String query = "UPDATE aluno SET nome = ?, email = ?, matricula = ?, data_nascimento = ? WHERE id = ?";

        try(Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(5, aluno.getId());

            stmt.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!");
        }
        return aluno;
    }

    // DELETE
    public void delete (int id) throws SQLException {
        String query = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Aluno deletado com sucesso!");
        }
    }
}