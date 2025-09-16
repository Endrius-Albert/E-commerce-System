package com.endrius.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

   private static final String URL = String.format(
    "jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC",
    System.getenv().getOrDefault("DB_HOST", "db"),
    System.getenv().getOrDefault("DB_PORT", "3306"),
    System.getenv().getOrDefault("DB_NAME", "ecommerce_db")
);
private static final String USER = System.getenv().getOrDefault("DB_USER", "ecommerce");
private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "36513411");

    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
