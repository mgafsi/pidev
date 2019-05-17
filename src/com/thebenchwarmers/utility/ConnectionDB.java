/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.utility;

/**
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDB {
    /// Attributes
    final String url="jdbc:mysql://127.0.0.1:3306/citedelaculture";
    final String login="root";
    final String mdp="";
    Connection connexion;
    static ConnectionDB instance;

    /// Private Constructor
    private ConnectionDB() {
    try{
        connexion=DriverManager.getConnection(url, login, mdp);
        System.out.println("You are in!");
        
    } catch (SQLException ex) {
        Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
    }

    
    
    
    }
    ///getters
    static public ConnectionDB getInstance() {
        if (instance==null)
            instance= new ConnectionDB();
        return instance;
    }


    public Connection getConnection() {
        return connexion;
    }
//     public static void main(String[] args) {
//        getInstance();
//        
//}
}

