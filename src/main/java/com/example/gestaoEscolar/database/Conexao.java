package com.example.gestaoEscolar.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/gestaoEscolar?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqlPW";

    public static Connection conexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
