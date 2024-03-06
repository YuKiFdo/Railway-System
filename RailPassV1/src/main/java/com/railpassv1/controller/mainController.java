package com.railpassv1.controller;

import com.railpassv1.model.loginModel;

import java.sql.*;
import java.util.Arrays;


public class mainController {
    private static Connection conn = null;
    private static String db = "jdbc:mysql://localhost:3306/railway?useSSL=false";
    private static String user = "root";
    private static String pass = "";
    
    public static boolean checkLogin(loginModel obj) {
        String Username = obj.getUsername();
        String Password = obj.getPassword();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db, user, pass);
        } catch (ClassNotFoundException | SQLException ee) {
            System.out.println(Arrays.toString(ee.getStackTrace()));
        }

        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, Username);
            state.setString(2, Password);

            ResultSet resultSet = state.executeQuery();
            if (resultSet.next()) {
                // User found with the provided username and password
                return true;
            } else {
                // No user found with the provided username and password
                return false;
            }
        } catch (SQLException ee) {
            System.out.println(Arrays.toString(ee.getStackTrace()));
            return false;
        }
    }
}
