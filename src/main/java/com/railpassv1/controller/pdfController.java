/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.railpassv1.controller;
import java.io.IOException;
import java.awt.Desktop;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import com.railpassv1.model.ticketIssueModel;
import java.io.File;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author yukif
 */
public class pdfController {
    
    public boolean generateTrainTicket(ticketIssueModel obj) {
        String date = obj.getDate();
        String time = obj.getTime();
        String name = obj.getName();
        String nic = obj.getNic();
        String train = obj.getTrain();
        String tclass = obj.gettClass();
        String from = obj.getFrom();
        String to = obj.getDestination();
        double total = obj.getTotal();
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();

            // Date and Time
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Date: " + date);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Time: " + time);

            // Passenger Details
            contentStream.newLineAtOffset(0, -40);
            contentStream.showText("Passenger Details:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Name: " + name);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("NIC: " + nic);

            // Train Details
            contentStream.newLineAtOffset(0, -60);
            contentStream.showText("Train Details:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Train Name: " + train);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Class: " + tclass);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("From: " + from);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("To: " + to);

            // Total Price
            contentStream.newLineAtOffset(0, -80);
            contentStream.showText("Total Price: " + total);

            contentStream.endText();
            contentStream.close();

            File tempFile = File.createTempFile("train_ticket", ".pdf");
            document.save(tempFile);
            
            printPDF(tempFile);
            
            document.close();
            
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private static void printPDF(File fileToPrint) {
        try {
            Desktop.getDesktop().print(fileToPrint);
        } catch (IOException e) {
            System.out.println("Error opening print dialog: " + e.getMessage());
        }
    }
}
