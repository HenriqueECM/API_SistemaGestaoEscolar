package com.example.gestaoEscolar.repository;

import com.example.gestaoEscolar.database.Conexao;
import com.example.gestaoEscolar.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaDAO {
    public Aula create (Aula aula)throws SQLException {
        String query = "INSERT INTO aula (turma_id, data_hora, assunto) VALUES (?,?,?)";

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, aula.getTurma_id());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                aula.setId(rs.getInt(1));
            }

//            String turmaQuery = "SELECT nome FROM turma WHERE id = ?";
//            try (PreparedStatement turmaStmt = conn.prepareStatement(turmaQuery)) {
//                turmaStmt.setInt(1, aula.getTurma_id());
//                ResultSet turmaRs = turmaStmt.executeQuery();
//                if (turmaRs.next()) {
//                    aula.setNomeTurma(turmaRs.getString("nome"));
//                }
//            }
        }
        return buscarPorId(aula.getId());
    }

    public Aula update (Aula aula)throws SQLException {
        String query = "UPDATE aula SET turma_id = ?, data_hora = ?, assunto = ? WHERE id = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, aula.getTurma_id());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());

            stmt.executeUpdate();
        }
        return aula;
    }

    public void delete (int id)throws SQLException {
        String query = "DELETE FROM aula WHERE id = ?";

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public boolean verificarExistencia (int id) throws SQLException {
        String query = "SELECT id, turma_id, data_hora, assunto FROM aula WHERE id = ?";

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

    public List<Aula> buscarTodos()throws SQLException {
        String query = """
            SELECT a.id, a.turma_id, t.nome AS nome_turma, a.data_hora, a.assunto
            FROM aula a
            JOIN turma t ON a.turma_id = t.id
        """;

        List<Aula> aulaList = new ArrayList<>();

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                String nomeTurma = rs.getString("nome_turma");
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");

                var aula = new Aula(id, turmaId, nomeTurma, dataHora, assunto);
                aulaList.add(aula);
            }
        }
        return aulaList;
    }

    public Aula buscarPorId(int id) throws SQLException {
        String query = """
            SELECT a.id, a.turma_id, t.nome AS nome_turma, a.data_hora, a.assunto
            FROM aula a
            JOIN turma t ON a.turma_id = t.id
            WHERE a.id = ?
        """;

        try (Connection conn = Conexao.conexao();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int idNew = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                String nomeTurma = rs.getString("nome_turma");
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto  = rs.getString("assunto");

                return new Aula(idNew, turmaId, nomeTurma, dataHora, assunto);
            }
            return null;
        }
    }
}
