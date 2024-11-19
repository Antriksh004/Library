/*9
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author ANTRIKSH
 */
public class AdminUserUpdatePanel extends JFrame{
    AdminUserUpdatePanel(String id, String type){
        setTitle("Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        Database db = new Database();
        setLayout(new GridLayout(3,3,20,25));
        
        String[] user = db.getUserById(id, type);
        if(user != null){
            
            JLabel usernameLabel = new JLabel("Username: ");
            JLabel passwordLabel = new JLabel("Password: ");
            JLabel typeLabel = new JLabel("Type: ");
            JLabel mobileLabel = new JLabel("Mobile: ");
            JLabel idLabel = new JLabel("University Id: ");
            JTextField usernameTextField = new JTextField(user[0]);
            JTextField passwordTextField = new JTextField( user[1]);
            JTextField typeTextField = new JTextField( user[2]);
            JTextField mobileTextField = new JTextField( user[3]);
            JTextField idTextField = new JTextField(user[4]);
            
            add(usernameLabel);
            add(usernameTextField);

            add(passwordLabel);
            add(passwordTextField);

            add(typeLabel);
            add(typeTextField);

            add(mobileLabel);
            add(mobileTextField);

            add(idLabel);
            add(idTextField);
            
            JButton submitAdminUserUpdationButton = new JButton("Update");
            add(submitAdminUserUpdationButton);
            
            submitAdminUserUpdationButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String updatedUsername = usernameTextField.getText();
                String updatedPassword = passwordTextField.getText();
                String updatedType = typeTextField.getText();
                String updatedMobile = mobileTextField.getText();
                String updatedId = idTextField.getText();
                
                System.out.println(updatedUsername + updatedPassword + updatedType + updatedMobile + updatedId);
                Database db = new Database();
                if(db.UpdateUserByAdmin(updatedUsername, updatedPassword, updatedType, updatedMobile, updatedId, user[4])){
                    
                    java.util.List<Student> students = db.getStudents();
                    
                    dispose();
                    AdminUserControlPanel aucp = new AdminUserControlPanel(students);
                    aucp.setVisible(true);
                }
                
                
            }
            });
            
        }
        
                
        
        
    }
    public static void main(String[] args){
//        String username = "antriksh";
//        String password = "antriksh";
        String id  = "antriksh";
        String userType = "student";
//        String mobile = "9874126509";
//        String hint = "Hint";
        new AdminUserUpdatePanel(id, userType);
    }
}
