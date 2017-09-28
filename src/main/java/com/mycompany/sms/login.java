package com.mycompany.sms;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.sql.*;

@Named(value = "login")
@RequestScoped
public class login {
    private String username,password;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    String name;
    String pass;
    private String msg = "no";
    
    public login() {
        DBConnect();
    }
    
    private void DBConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/msgdb", "root", "");
            st = con.createStatement();
            msg = "Connected";
        }catch(Exception ex){
            msg = ex.toString();
        }
    }
    
    public void setUsername(String username){
        this.username = username ; 
    }
    public String getUsername(){
        return this.username ;
    }
    public void setPassword(String password){
        this.password =  password ; 
    }
    public String getPassword(){
        return this.password ;
    }
    
    public void Submit() throws SQLException{
        if(username != null || password != null){
            searchData(username, password);
            //return "faces/registered" ;
        }
        //return ""; 
    }
    
    public void searchData(String name1 , String password1) {
         try {
             String query = "select * from users where username like '"+name1+"'";
             rs = st.executeQuery(query);
             if(rs.next()){
                 name = rs.getString("username");
                 pass = rs.getString("password");
                 if(pass.equals(password1)){
                     msg = "Done : " + "name = "+name+" , password = "+ pass;
                 }else{
                     msg = "Invalid password";
                 }
             }else{
                 msg = "Invalid username";
             }

         } catch(Exception ex) {
             msg = ex.toString();
          }
    }
    
    public String printMsg(){
        return this.msg ;
    }   
}