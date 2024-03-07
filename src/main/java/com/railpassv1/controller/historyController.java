/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.controller;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author yukif
 */
public class historyController {
    
    public void getData(DefaultTableModel tableModel) {
        Connection conn = null;

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
    
}
