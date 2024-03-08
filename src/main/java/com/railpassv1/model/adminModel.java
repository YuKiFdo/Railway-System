/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.model;

/**
 *
 * @author yukif
 */
public class adminModel {
    String password;
    String confirmPassword;
    String FirstClass;
    String SecondClass;
    String ThirdClass;
    String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstClass() {
        return FirstClass;
    }

    public void setFirstClass(String FirstClass) {
        this.FirstClass = FirstClass;
    }

    public String getSecondClass() {
        return SecondClass;
    }

    public void setSecondClass(String SecondClass) {
        this.SecondClass = SecondClass;
    }

    public String getThirdClass() {
        return ThirdClass;
    }

    public void setThirdClass(String ThirdClass) {
        this.ThirdClass = ThirdClass;
    }
    
}
