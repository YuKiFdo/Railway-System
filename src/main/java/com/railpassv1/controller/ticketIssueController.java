/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.controller;
import com.railpassv1.model.ticketIssueModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author yukif
 */
public class ticketIssueController {
    
    public void Calculate (ticketIssueModel obj) {
        String from = obj.getFrom();
        String to = obj.getDestination();
        boolean age = obj.isAge();
        String tclass = obj.gettClass();
        Double price = 0.00;
        
        switch (tclass) {
            case "First Class" -> price += 800;
            case "Second Class" -> price += 600;
            case "Third Class" -> price += 350;
            default -> {
            }
        }
        
        if (from.equals("Anuradhapura")){
            if (to.equals("Colombo")) {
                price += 400;
            }
            else if (to.equals("Galle") ){
                price += 600;
            }
        }
        else if (from.equals("Ampara")) {
             if (to.equals("Colombo")) {
                price += 250;
            }
            else if (to.equals("Galle") ){
                price += 450;
            }
        }
        
        if (!age) {
            price -= 400;
        }
         
        obj.setTotal(price);
        // Others will go like this
    }   
    
    public static void SaveDetails(ticketIssueModel obj) {
        String date = obj.getDate();
        String time = obj.getTime();
        String name = obj.getName();
        String nic = obj.getNic();
        String train = obj.getTrain();
        String tclass = obj.gettClass();
        String from = obj.getFrom();
        String to = obj.getDestination();
        double total = obj.getTotal();

        Connection conn = null;
        try {
            conn = databaseController.connect();

            String query = "INSERT INTO tickets (date, time, name, nic, train, tclass, from_location, to_location, total) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement state = conn.prepareStatement(query)) {
                state.setString(1, date);
                state.setString(2, time);
                state.setString(3, name);
                state.setString(4, nic);
                state.setString(5, train);
                state.setString(6, tclass);
                state.setString(7, from);
                state.setString(8, to);
                state.setDouble(9, total);

               state.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            databaseController.closeConnection(conn);
        }
    }
}
