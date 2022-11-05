/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author minhd
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
    public static Properties getSiteMap(String siteMapsFile, ServletContext context)
    throws IOException{
        if(siteMapsFile == null){
            return null;
        }
        if (siteMapsFile.trim().isEmpty()) {
            return null;
        }
        Properties result = null;
        InputStream is =null;
        try {
            is = context.getResourceAsStream(siteMapsFile);
            result = new Properties();
            result.load(is);
            return result;
        } finally{
            if(is != null){
                is.close();
            }
        }
    }
    
}
