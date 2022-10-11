/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author loqua
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection()
            throws /*lassNotFoundException*/ NamingException, SQLException{
        //1. Find context of Server - JNDI (Java Naming Directories Interface)
        Context serverContext = new InitialContext();
        //2. Find context of Container
        Context tomcatContext = (Context)serverContext.lookup("java:comp/env");
        //3. Find DS
        DataSource ds = (DataSource)tomcatContext.lookup("007Spy");
        //4. Open Connection
        Connection con = ds.getConnection();
        return con;
//        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create URL Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJFall2022DB;instanceName=QUANGTHANG";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        //password cua sa phai la chuoi rong
//        
//        return con;
    }
}
