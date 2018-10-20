/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package postgresjdbc;

import java.io.*;
import java.sql.*;

/**
 *
 * @author ahmed
 */
public class PostgresJDBC {
	
	
    public boolean logIn(String un, String pass){
        boolean res = true;
        try
        {
        	//login to ecs database
        	//need to use Ali's db, cos mine won't load properly
            String databaseUser = "ahmed";
            String databaseUserPass = "tmp123";           
            Class.forName("org.postgresql.Driver");
            Connection connection = null;            
            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from crookfion_users where name='"+un+"' and password='"+pass+"'");
            if(! rs.next())
                res = false;            
            rs.close();
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("LogIn Error: "+e.toString());
            e.printStackTrace();
            res = false;
        }
        return res;
    }  //end login

    public String defineWord(String word) {
    	String define=null;
    	
    	try
        {
        	//login to ecs database
        	//need to use Ali's db, cos mine won't load properly
            String databaseUser = "ahmed";
            String databaseUserPass = "tmp123";           
            Class.forName("org.postgresql.Driver");
            Connection connection = null;            
            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select definition from crookfion_dictionary where word='"+word);
            if(rs.next()) {
                define=rs.toString();  
            }
            rs.close();
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("LogIn Error: "+e.toString());
            e.printStackTrace();
            //res = false;
        }
    	 	
    	return define;
    
    } //end defineWord
    
    
}