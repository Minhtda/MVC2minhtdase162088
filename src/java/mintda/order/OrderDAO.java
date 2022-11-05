/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mintda.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import minhtda.utils.DBHelper;

/**
 *
 * @author minhd
 */
public class OrderDAO implements Serializable {

    public OrderDTO addOrder(String name, Date dbuy, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO result = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CURD
                //2.1 Create a SQL String
                String sql = "Insert into [Order](DateBuy, Total, Name) "
                        + "Values(?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setDate(1, dbuy);
                stm.setFloat(2, total);
                stm.setString(3, name);
                //2.2 Create a Statement Object
                //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    result = new OrderDTO(name, dbuy, total);
                }
                //2.3 Execute Query --> ResultSet
                //Muon dung ResultSet thi phai len tren tao
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int findOrderID(String name, Date dbuy, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                System.out.println("ket noi dc");
                //2. CURD
                //2.1 Create a SQL String
                String sql = "Select MAX(OrderID) "
                        + "From [Order] "
                        + "Where DateBuy = ? And Total = ? And Name = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, dbuy);
                stm.setFloat(2, total);
                stm.setString(3, name);
                //2.2 Create a Statement Object
                //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
                rs = stm.executeQuery();

                if (rs.next()) {
                    result = rs.getInt(1);
                }
                System.out.println(result);
                //2.3 Execute Query --> ResultSet
                //Muon dung ResultSet thi phai len tren tao
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
