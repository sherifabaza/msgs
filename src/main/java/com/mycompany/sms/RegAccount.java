package com.mycompany.sms;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mohamed
 */
@Named(value = "regAccount")
@RequestScoped
public class RegAccount {
private String username,password,repassword,email = "",gender,msg = "no" ;
private PreparedStatement pstmt;
Connection  conn ;
public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    

    public RegAccount() {
        initializeJdbc();
        
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
    public void setRepassword(String repassword){
        this.repassword =  repassword ; 
    }
    public String getRepassword(){
        return this.repassword ;
    }
       public void setEmail(String mail){
        this.email = mail ; 
    }
    public String getEmail(){
        return this.email ;
    }
    
    public void setGender(String gender){
        this.gender = gender ; 
    }
    public String getGender(){
        return this.gender ;
    }
      
    
    
    
 private void initializeJdbc() {       
    try {

        Class.forName("com.mysql.jdbc.Driver");
        msg = "connected" ; 
        conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/smsapp","root","01121785945");
        pstmt = conn.prepareStatement("insert into user(username,"
        + "userpassword,mail,gender) "
        + " values(?, ?, ?, ?)");

    }
    catch (Exception ex) {
        msg = ex.toString();
        System.out.println(ex);
    }
 }
 public void storeUser() throws SQLException{
    try{ 
        
        pstmt.setString(1,username);
        pstmt.setString(2,this.password);
        pstmt.setString(3,email);
        pstmt.setString(4,gender);
        pstmt.executeUpdate();
      conn.close(); 
    }catch (Exception ex) {
        msg= ex.toString(); 
}
}
 
 public String Submit() throws SQLException{
     if(username != null|| password != null|| email != null){
      // if( validate(this.email) && this.password == this.repassword ){
           storeUser();
            msg = "registered" ; 
            return "faces/registered" ;
      // }
     }
     return ""; 
     
    
 }
    
    public String printMsg(){
        return this.msg ;
    }
 
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
}
