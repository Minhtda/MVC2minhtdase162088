/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mintda.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author minhd
 */
public class OrderDTO implements Serializable{
    private int OrderID;
    private String name;
    private Date dbuy;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO( String name, Date dbuy, float total) {
        this.name = name;
        this.dbuy = dbuy;
        this.total = total;
    }

    
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDbuy() {
        return dbuy;
    }

    public void setDbuy(Date dbuy) {
        this.dbuy = dbuy;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
