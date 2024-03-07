/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.controller;
import java.sql.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yukif
 */
public class historyController {
    Connection conn = null;
    
    public void getData(DefaultTableModel tableModel) {
        

        try {
            conn = databaseController.connect();
            String query = "SELECT * FROM tickets";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            int col = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Object[] rows = new Object[col];
                for (int i = 1; i <= col; i++) {
                    rows[i - 1] = rs.getObject(i);
                }

                tableModel.addRow(rows);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            databaseController.closeConnection(conn);
        }
    }
    
//     public ResultSet getAllTicketsbyType(String type){
//        
//        try{
//            conn = databaseController.connect();
//            String query = "SELECT * FROM tickets WHERE train = ?";
//            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, type);
//            
//            // Execute the query and retrieve the result set
//            ResultSet results = statement.executeQuery();
//            
//            return results;
//
//        }
//        catch (SQLException e){
//            System.out.println("SQL Error occurred");
//        }
//        return null;
//    }
    
    public ResultSet getAllTicketsbyTypeAndDate(String type, String date) {
    try {
        conn = databaseController.connect();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM tickets WHERE 1");

        if (type != null) {
            queryBuilder.append(" AND train = ?");
        }

        if (date != null) {
            queryBuilder.append(" AND date = ?");
        }

        PreparedStatement statement = conn.prepareStatement(queryBuilder.toString());

        int parameterIndex = 1; // Parameter index for PreparedStatement

        if (type != null) {
            statement.setString(parameterIndex++, type);
        }

        if (date != null) {
            statement.setString(parameterIndex, date);
        }

        // Execute the query and retrieve the result set
        ResultSet results = statement.executeQuery();

        return results;

    } catch (SQLException e) {
        System.out.println("SQL Error occurred: " + e.getMessage());
        e.printStackTrace(); // Print the stack trace for debugging purposes
    } 

    return null;
    }
    
}
