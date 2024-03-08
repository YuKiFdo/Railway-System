/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.controller;

import com.railpassv1.model.adminModel;

import java.sql.*;

/**
 *
 * @author yukif
 */
public class adminController {
    
    Connection conn = null;
    
    public double getTicketsTotal() {
        try {
            conn = databaseController.connect();
        
            String query = "SELECT SUM(total) AS total FROM tickets";

            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double total = resultSet.getDouble("total");
                return total;
            } else {
               return 0.0;
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            return 0.0;
       }
    }
    
    public int getSales() {
        try {
            conn = databaseController.connect();
        
            String query = "SELECT COUNT(id) AS id FROM tickets";

            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int total = resultSet.getInt("id");
                System.err.println(total);
                return total;
            } else {
               return 0;
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            return 0;
       }
    }
    
      public double getTicketPrice(String ticketType) {
        double price = 0.0;

        try {
             conn = databaseController.connect();
            String query = "SELECT price FROM tickettype WHERE type = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, ticketType);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        price = resultSet.getDouble("price");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

        return price;
    }
      
    public void updateTicketPrice(String ticketType, String newPrice) {
        try {
            double price = Double.parseDouble(newPrice);
            
            
            
            try {
                conn = databaseController.connect();
                String query = "UPDATE tickettype SET price = ? WHERE type = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setDouble(1, price);
                    preparedStatement.setString(2, ticketType);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                 System.err.println("Error executing SQL query: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
           System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
    
    public void updatePasswordInDatabase(adminModel obj) {
    PreparedStatement pstmt = null;
    String newPassword = obj.getConfirmPassword();
    try {
         conn = databaseController.connect();

        
        String updateQuery = "UPDATE users SET password = ? WHERE username = ?";
        pstmt = conn.prepareStatement(updateQuery);

      
        pstmt.setString(1, newPassword);
        pstmt.setString(2, "admin"); 

        
        pstmt.executeUpdate();

        System.out.println("Password updated successfully");
    } catch (SQLException e) {
        System.err.println("Error executing SQL query: " + e.getMessage());
    }
    }
}
