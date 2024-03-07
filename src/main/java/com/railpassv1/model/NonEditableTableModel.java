/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yukif
 */
public class NonEditableTableModel extends DefaultTableModel {
    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
}
