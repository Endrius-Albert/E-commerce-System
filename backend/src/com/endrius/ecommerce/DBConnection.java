package com.endrius.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_HOST = System.getenv().getOrDefault("DB_HOST", "db");
    private static final String DB_PORT = System.getenv().getOrDefault("DB_PORT", "3306");
    private static final String DB_NAME = System.getenv().getOrDefault("DB_NAME", "ecommerce_db");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "ecommerce_user");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "36513411");

    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
