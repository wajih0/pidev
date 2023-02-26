/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wajih ben hmida
 */
public class DataSource {
      final String URL ="jdbc:mysql://127.0.0.1:3306/wajih";
    final String USERNAME="root";
    
    
    final String PWD ="";
   private  Connection cnx;
 private static   DataSource instance;
    private DataSource(){
        try {
            cnx =DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected ....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static DataSource getInstance (){
        if (instance == null)
            instance = new DataSource();
        return instance ;
    }
    public Connection getCnx(){
        return cnx;
    }
}
