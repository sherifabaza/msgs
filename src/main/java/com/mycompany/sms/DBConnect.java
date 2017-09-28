package com.mycompany.sms;

import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "DBConnect")
@RequestScoped
public class DBConnect {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    String name;
    String password;
    private String msg = "no";
    DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/msgdb", "root", "");
            st = con.createStatement();
            msg = "Connected";
            //System.out.println("Done");
        }catch(Exception ex){
            msg = ex.toString();
            //System.out.println("Error: "+ex);
        }
    }
    /*
     public void getData() {
         try {
             String query = "select * from users";
             rs = st.executeQuery(query);
             System.out.println("Records from Database");
             while (rs.next()) {
                 String name = rs.getString("name");
                 String mail = rs.getString("mail");
                 String mobile = rs.getString("mobile");
                 String job = rs.getString("job");
                 System.out.println("Name: " + name + " mail: " + mail + " mobile: " + mobile + " job: " + job);
             }
         } catch(Exception ex) {
             System.out.println(ex);
          }
    }
    */
     public void searchData(String name1 , String password1) {
         try {
             String query = "select * from users where name like '"+name1+"'";
             rs = st.executeQuery(query);
             //System.out.println("Records from Database for your search ");
             while (rs.next()) {
                 name = rs.getString("username");
                 password = rs.getString("password");
                 if(password.equals(password1)){
                     //System.out.println("Done");
                     msg = "Done : " + "name = "+name+" , password = "+ password;
                     //submit(name , password);
                 }else{
                     //System.out.println("Error login");
                     //submit(name1 , "ERROR");
                     msg = "Error login";
                 }
                 //System.out.println("Name: " + name + " mail: " + mail + " mobile: " + mobile + " job: " + job);
             }
         } catch(Exception ex) {
             msg = ex.toString();
             //System.out.println(ex);
          }
    }
     /*
     public void addData(String name1, String pass1, String mail1) {
         try {
         
         String query = "insert into employees2 values ('"+name1+"','"+mail1+"','"+mobile1+"','"+job1+"')";
         
         st.execute(query);
         
         System.out.println("addData Done");
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
   }
     */
/*
     public void deleteData(String name1) {
         try {
             String query = "delete from employees2 where name = '"+name1+"'";
         
             st.execute(query);
         
             System.out.println("deleteData Done");
         } catch(SQLException e) {
                System.out.println("SQL exception occured" + e);
           }
   }
     */
     /*
     public void updateData(String name1 , String mail) {
         try {
         
         String query = "update employees2 set mail = '"+mail+"' where name = '"+name1+"'";
         
         st.execute(query);
         
         System.out.println("updateData Done");
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
   }
     */
     
     public String printMsg(){
        return this.msg ;
    }
}