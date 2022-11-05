/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.orderDetail;

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
 * @author minhd
 */
public class OrderDetailDAO implements Serializable {

    private List<OrderDetailDTO> orderDetailList;

    public List<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public boolean addOrderDetail(String sku, int orderID, int quanity, float price, float total) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            System.out.println("da den dao");
            con = DBHelper.makeConnection();
            if (con != null) {
                System.out.println("da ket noi");
                String sql = "Insert into [OrderDetail]"
                        + "(sku, orderID, quantity, price, total) "
                        + "Values(?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, sku);
                ps.setInt(2, orderID);
                ps.setInt(3, quanity);
                ps.setFloat(4, price);
                ps.setFloat(5, total);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    result = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return result;
    }
    public void showOrderDetail(int orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                System.out.println("da ket noi");
                String sql = "Select orderDetailID, sku, orderID, quantity, price, total "
                        + "From [OrderDetail] "
                        + "Where orderID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderDetailID = rs.getInt("orderDetailID");
                    String sku = rs.getString("sku");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    float total = rs.getFloat("total");
                    OrderDetailDTO oDList = new OrderDetailDTO(orderDetailID, 
                            sku, orderID, quantity, price, total);
                    if (this.orderDetailList == null) {
                        this.orderDetailList = new ArrayList<>();
                    }
                    orderDetailList.add(oDList);
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
