```java
codigo para transformar uma lista de ids em lista de nomes:





import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PreparedStatementExample {
    public static void main(String[] args) {
        List<Integer> ids = List.of(1, 2, 3); // Exemplo de lista de IDs
        String sql = "SELECT * FROM TESTE IN " + generateInClauseSql(ids.size());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco", "usuario", "senha");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preencher os parâmetros do PreparedStatement
            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i)); // Setando o valor no parâmetro correto
            }

            // Executar a consulta
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Processar os resultados
                System.out.println("ID: " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Gera a parte da consulta com placeholders (?)
    private static String generateInClauseSql(int size) {
        StringBuilder sql = new StringBuilder("(");
        for (int i = 0; i < size; i++) {
            sql.append("?");
            if (i < size - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}
```
