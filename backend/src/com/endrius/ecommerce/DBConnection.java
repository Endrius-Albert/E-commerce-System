package com.endrius.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    
    private static final String USER = "ecommerce_user";           
    private static final String PASSWORD = "36513411";  

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

