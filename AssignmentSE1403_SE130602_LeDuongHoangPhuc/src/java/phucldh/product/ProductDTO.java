/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.product;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ProductDTO implements Serializable{
    private int productId;
    private String productName;

    public ProductDTO(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public ProductDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
}
