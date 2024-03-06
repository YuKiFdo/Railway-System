package com.railpassv1.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class databaseController {
    private static Connection conn = null;
    private static String db = "jdbc:mysql://localhost:3306/railway?useSSL=false";
    private static String user = "root";
    private static String pass = "";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(db, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new SQLException("Failed to connect to the database");
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log the exception or handle it appropriately
                e.printStackTrace();
            }
        }
    }
}
