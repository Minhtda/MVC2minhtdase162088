/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtda.utils.DBHelper;

/**
 *
 * @author loqua
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
    throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
        //1. connect DB
        //open connection
        con = DBHelper.makeConnection();
        if(con != null){
        //2. CURD
        //2.1 Create a SQL String
        String sql = "Select username "
                + "From Registration "
                + "Where username = ? And password = ?";
        //2.2 Create a Statement Object
        //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
        stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        //2.3 Execute Query --> ResultSet
        //Muon dung ResultSet thi phai len tren tao
        rs = stm.executeQuery();
        //2.4 Process ResultSet
        //Tra ve nhieu dong du lieu thi su dung while
        //Tra ve 1 dong du lieu thi su dung if
        if(rs.next()){
            result = true;
            }
        }//end con is available
        
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
    public void searchLastName(String searchValue)throws SQLException,
             NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
        //1. connect DB
        //open connection
        con = DBHelper.makeConnection();
        if(con != null){
        //2. CURD
        //2.1 Create a SQL String
        String sql = "Select username, password, lastname, isAdmin "
                + "From Registration "
                + "Where lastname Like ?";
        //2.2 Create a Statement Object
        //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
        stm = con.prepareStatement(sql);
        stm.setString(1, "%" + searchValue + "%");
        //2.3 Execute Query --> ResultSet
        //Muon dung ResultSet thi phai len tren tao
        rs = stm.executeQuery();
        //2.4 Process ResultSet
        //Tra ve nhieu dong du lieu thi su dung while
        //Tra ve 1 dong du lieu thi su dung if
        while(rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fullName = rs.getString("lastname");
            boolean role = rs.getBoolean("isAdmin");
            RegistrationDTO dto = 
                    new RegistrationDTO(username, password, fullName, role);
            if(this.accountList == null){
                this.accountList = new ArrayList<>();
            }//end account List had not existed
            this.accountList.add(dto);
        }//end result traverse
        }//end con is available
        
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
    }
    public boolean deleteAccount(String Username)throws SQLException,
             NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
        //1. connect DB
        //open connection
        con = DBHelper.makeConnection();
        if(con != null){
        //2. CURD
        //2.1 Create a SQL String
        String sql = "Delete "
                + "From Registration "
                + "Where username = ?";
        stm = con.prepareStatement(sql);
        stm.setString(1, Username);
        int effectRows = stm.executeUpdate();
        if(effectRows>0){
            result = true;
        }
        }//end con is available
        
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    public boolean updateAccount(String Username, String password,boolean isRoll)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
        //1. connect DB
        //open connection
        con = DBHelper.makeConnection();
        if(con != null){
        //2. CURD
        //2.1 Create a SQL String
        String sql = "Update Registration "
                + "Set password = ?, isAdmin = ? "
                + "Where username = ?";
        stm = con.prepareStatement(sql);
        stm.setString(1, password);
        stm.setBoolean(2, isRoll);
        stm.setString(3, Username);
        int effectRows = stm.executeUpdate();
        if(effectRows>0){
            result = true;
        }
        }//end con is available
        
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
}