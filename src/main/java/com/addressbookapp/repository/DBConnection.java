package com.addressbookapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // URL is not sensitive → keep in code
    private static final String URL =
            "jdbc:mysql://localhost:3306/addressbook_db?useSSL=false&serverTimezone=UTC";

    // Credentials from environment variables
    private static final String USERNAME = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (USERNAME == null || PASSWORD == null) {
            throw new SQLException("Database credentials not found in environment variables.");
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}