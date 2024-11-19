package com.mycompany.librarymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class Database {
    String AdminAccessCode = "admin";
    String driver = "com.mysql.cj.jdbc.Driver";

    String url = "jdbc:mysql://localhost:3306/librarymanagementsystem"; 
    String databaseUsername = "root"; 
    String databasePassword = "Antriksh"; 
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, databaseUsername, databasePassword); 
        System.out.println("conn=" + conn);
        

        return conn;
        
        

        
        
    }
    
    public boolean InsertStudentOrStaffData(String username, String password, String type, String mobile, String universityid){
        String query = null;
        try {       
            Connection con = getConnection();
            
            if("staff".equals(type)){
                query = "INSERT INTO staff (username, password, type, mobile, universityid) VALUES ('" + username + "', '" + password + "', '" + type + "', '" + mobile + "', '"+universityid+"');"; // Proper SQL syntax
 
                
            }
            else{
                query = "INSERT INTO student (username, password, type, mobile, universityid) VALUES ('" + username + "', '" + password + "', '" + type + "', '" + mobile + "', '"+universityid+"');"; // Proper SQL syntax

            }
            System.out.println("Query is: " + query);
            
            //Create Statement
            Statement st = con.createStatement();
            int val = st.executeUpdate(query);

            System.out.println(val + " row(s) affected");
            con.close();
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    public boolean InsertAdminData(String username, String password, String adminAccessCode, String mobile, String universityid){
        if(AdminAccessCode.equals(adminAccessCode)){
            try {       
                Connection con = getConnection();


                String query = "INSERT INTO admin (username, password, mobile, universityid) VALUES ('" + username + "', '" + password + "', '" + mobile + "', '"+universityid+"');"; // Proper SQL syntax
                System.out.println("Query is: " + query);

                //Create Statement
                Statement st = con.createStatement();
                int val = st.executeUpdate(query);

                System.out.println(val + " row(s) affected");
                con.close();
                return true;
                } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        return false;
        
    }
    public boolean checkLogin(String username, String password){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        

        try {
            con = getConnection();
            String query = "select username,password from student ";
            // create a statement
            stmt = con.createStatement(); 

            rs = stmt.executeQuery(query); 

            
            while (rs.next()) {
                String clientUsername = rs.getString(1);
                String clientPassword = rs.getString(2);
                System.out.println("---------------");
                System.out.println("Name: " + clientUsername);
                System.out.println("Rollno: " + clientPassword);
                if(username.equals(clientUsername)){
                    if(password.equals(clientPassword)){
                        System.out.println("successfull login");
                        return true;
                        
                    }
                    else{
                        System.out.println("Incorrect Password");
                   
                    }
                }
                else{
                    System.out.println("never went for search");
                }
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release database resources
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean checkAdminLogin(String username, String password, String adminAccessCode){
        if(adminAccessCode.equals(AdminAccessCode)){
            Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        

        try {
            con = getConnection();
            String query = "select username,password from admin";
            // create a statement
            stmt = con.createStatement(); 

            rs = stmt.executeQuery(query); 

            
            while (rs.next()) {
                String clientUsername = rs.getString(1);
                String clientPassword = rs.getString(2);
                System.out.println("---------------");
                System.out.println("Name: " + clientUsername);
                System.out.println("Rollno: " + clientPassword);
                if(username.equals(clientUsername)){
                    if(password.equals(clientPassword)){
                        System.out.println("successfull login");
                        return true;
                        
                    }
                    else{
                        System.out.println("Incorrect Password");
                        return false;
                   
                    }
                }
                else{
                    System.out.println("never went for search");
                }
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release database resources
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      
            
        }
        return false;
        
    }
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student ORDER BY username ASC")) {

            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String major = rs.getString("major");
                String username = rs.getString("username");
                String mobile = rs.getString("mobile");
                String universityId = rs.getString("universityid");
                

                students.add(new Student(username, mobile, universityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    public String[] getUserById(String userId, String type){
        
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM student "
                    + "WHERE universityid = '"+userId +"'";
            if("staff".equals(type)){
                query = "SELECT * FROM staff "
                    + "WHERE universityid = "+userId;
            }
            System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("type");
    
                String mobile = rs.getString("mobile");
                String universityId = rs.getString("universityid"); 
                String[] result = {username,password,userType, mobile, universityId};
                return result;
            }
            
            
        }catch(SQLException e){
            System.out.println("SQL exception occured!");
            
        }catch(ClassNotFoundException ce){
            System.out.println("class not found exception occured");
        }
        return null;
        
    }
    public boolean UpdateUserByAdmin(String username,String password, String type, String mobile,String updatedId, String id){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "DELETE FROM student WHERE universityid = '" + id  + "';";
            System.out.println(query);
                            
            if("staff".equals(type)){
                query = "DELETE FROM staff WHERE universityid = '" + id  + "';";
            }
            
            System.out.println(query);
            
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected);
            boolean isInserted = InsertStudentOrStaffData(username, password, type, mobile, updatedId);
            return isInserted;
            
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException ce){
            System.out.println(ce);
        }
        return false;
    }
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        String username = "Antriksh";
//        String password = "Password";
//        Database db = new Database(username, password);
//        db.InsertData();
//        
//        
//
//        
//        
//    }
}
