/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.orderDetail;

import java.io.Serializable;

/**
 *
 * @author minhd
 */
public class OrderDetailDTO implements Serializable{
    private int OrderDetailID;
    private String sku;
    private int orderID;
    private int quantity;
    private float price;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int OrderDetailID, String sku, int orderID, int quantity, float price, float total) {
        this.OrderDetailID = OrderDetailID;
        this.sku = sku;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    
    

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }
    

    
    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
}
