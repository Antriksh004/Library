/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author ANTRIKSH
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminUserControlPanel extends JFrame {
    public AdminUserControlPanel(List<Student> students) {
        setTitle("Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        JButton homeButton = new JButton("Home");
        add(homeButton, BorderLayout.NORTH);
        homeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Home h = new Home();
                h.setVisible(true);
                
            }
            
        });
                
        for (Student student : students) {
            JPanel card = createStudentCard(student);
            cardsPanel.add(card);
            
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createStudentCard(Student student) {
        JPanel card = new JPanel();
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(250, 150));

        JLabel nameLabel = new JLabel("Username: " + student.getUsername());
        JLabel ageLabel = new JLabel("Mobile: " + student.getMobile());
        JLabel majorLabel = new JLabel("University Id: " + student.getUniversityId());
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");
        
            

        card.add(nameLabel);
        card.add(ageLabel);
        card.add(majorLabel);
        card.add(deleteButton);
        card.add(updateButton);
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Home h = new Home();
                h.setVisible(true);
                
            }
            
        });
        updateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                AdminUserUpdatePanel auup= new AdminUserUpdatePanel(student.getUniversityId(),"student"); 
                auup.setVisible(true);
               
               
                
            }
            
        });
        
        return card;
    }

    public static void main(String[] args) {
        Database db = new Database();
        List<Student> students = db.getStudents();
        new AdminUserControlPanel(students);
    }
}
