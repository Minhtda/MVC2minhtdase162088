/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtda.registration.RegistrationDTO;
import minhtda.utils.DBHelper;

/**
 *
 * @author minhd
 */
public class ProductDAO implements Serializable {
    
    private List<ProductDTO> productList;

    public List<ProductDTO> getProductListt() {
        return productList;
    }

    public void showAll() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            //1. connect DB
            //open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CURD
                //2.1 Create a SQL String
                String sql = "Select sku, name, description, price, quanity, status "
                        + "From Product "
                //+ "Where username = ? And password = ?"
                        + "Where quanity > 0 And status = ?";
                //2.2 Create a Statement Object
                //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, true);
                //2.3 Execute Query --> ResultSet
                //Muon dung ResultSet thi phai len tren tao
                rs = stm.executeQuery();
                //2.4 Process ResultSet
                //Tra ve nhieu dong du lieu thi su dung while
                //Tra ve 1 dong du lieu thi su dung if
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quanity = rs.getInt("quanity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(sku, name, description, price, quanity, status);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }//end account List had not existed
                    this.productList.add(dto);
                    
                }
            }//end con is available

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
    }
    public int getQuantity(String sku) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1. connect DB
            //open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CURD
                //2.1 Create a SQL String
                String sql = "Select quanity "
                        + "From Product "
                //+ "Where username = ? And password = ?"
                        + "Where sku = ?";
                //2.2 Create a Statement Object
                //Muon tao PrepareStatement phai len tren tao bien PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                //2.3 Execute Query --> ResultSet
                //Muon dung ResultSet thi phai len tren tao
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("quanity");
                }
            }//end con is available

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
