package com.railpassv1.controller;

import com.railpassv1.model.loginModel;

import java.sql.*;

import java.util.Arrays;


public class loginController {
    
    public static boolean checkLogin(loginModel obj) {
        String username = obj.getUsername();
        String password = obj.getPassword();

        Connection conn = null;
        try {
            conn = databaseController.connect();

            String query = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement state = conn.prepareStatement(query)) {
                state.setString(1, username);
                state.setString(2, password);

                try (ResultSet resultSet = state.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        } finally {
            databaseController.closeConnection(conn);
        }
    }
}
