/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author ANTRIKSH
 */
public class Student {
    
    String username = "antriksh";
    String password = "password";
    String type = "student";
    String mobile = "66262626262";
    String universityId = "22bcs017";
    Student(String username, String mobile, String universityId){
        this.username = username;
        this.mobile = mobile;
        this.universityId = universityId;
    }
    

    
    public String getUsername(){
        return this.username;
        
    };
    public String getPassword(){
        return password;
    };
    public String getType(){
        return type;
    };
    public String getMobile(){
        return mobile;
    };
    public String getUniversityId(){
        return universityId;
    };
    
    
    
}
