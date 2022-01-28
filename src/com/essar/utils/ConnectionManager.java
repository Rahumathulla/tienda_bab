/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.utils;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahumathulla
 */
public class ConnectionManager {
    
    static Connection con = null;
    PropertyUtil propertyUtil = new PropertyUtil();
    Properties props = propertyUtil.readProperties();
    
 

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        try {
            String db_url = props.getProperty("db_url");
            String db_name=props.getProperty("db_name");
            String db_user=props.getProperty("db_user");
            String db_pwd=props.getProperty("db_pwd");
            con = DriverManager.getConnection(db_url+"/"+db_name, db_user, db_pwd);
        
    }
    catch (SQLException ex){
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
    }
        return con;
   }
    
}
