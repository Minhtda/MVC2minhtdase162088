/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.product;

import java.io.Serializable;

/**
 *
 * @author minhd
 */
public class ProductDTO implements Serializable{
    private String sku;
    private String name;
    private String description;
    private float price;
    private int quanity;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String sku, String name, String description, float price, int quanity, boolean status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quanity = quanity;
        this.status = status;
    }

    public ProductDTO(String sku, String name, float price, int quanity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quanity = quanity;
    }
    
    
    
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
