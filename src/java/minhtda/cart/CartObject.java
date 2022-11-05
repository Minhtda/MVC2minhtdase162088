/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.cart;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import minhtda.product.ProductDTO;

/**
 *
 * @author minhd
 */
public class CartObject implements Serializable{
    private Map<String, ProductDTO> items;

    public Map<String, ProductDTO> getItems() {
        return items;
    }
    public void addITemsToCart(ProductDTO dto){
        if(dto == null){
            return;
        }
        //1. check items(cart) exist 
        if(items == null){
            this.items = new HashMap<>();
            
        }
        //2. check item exist ==> inscrease quanity
        if(this.items.containsKey(dto.getSku())){
            int qua = items.get(dto.getSku()).getQuanity();
            dto.setQuanity(qua + 1);
        }
        this.items.put(dto.getSku(), dto);
    }
    public void removeItemFromCart(String sku){
        if(sku == null){
            return;
        }
        if(sku.trim().isEmpty()){
            return;
        }
        if (items == null) {
            return;
        }
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
        
    }
  }
