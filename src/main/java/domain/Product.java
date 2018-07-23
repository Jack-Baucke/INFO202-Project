/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author bauja773
 */
public class Product {
    private int productID;
    private String name;
    private String description;
    private String category;
    private BigDecimal listPrice;
    private Integer quantity;

    public Product(int productID, String name, String description, String category, BigDecimal listPrice, Integer quantity) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }    

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name=" + name + ", description=" + description + ", category=" + category + ", listPrice=" + listPrice + ", quantity=" + quantity + '}';
    }

    
    
}
