/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author bauja773
 */
public class Product {
    @NotNull(message = "ID must be provided.")
    @NotBlank(message = "ID must be provided.")
    @Length(min = 3, max = 50, message = "ID must contain at least three characters and no more than fifty.")
    private String productID;
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min = 2, max = 50, message = "Name must contain at least two characters.")
    private String name;
    @Length(max = 200)
    private String description;
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    @Length(min = 2, max = 30, message = "Category must be at least two characters.")
    private String category;
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    @Length(max = 12)
    private BigDecimal listPrice;
    @NotNull(message = "Quantity must be provided.")
    @NotNegative(message = "Quantity must be zero or greater.")
    @Length(max = 8)
    private Integer quantity;

    public Product(String productID, String name, String description, String category, BigDecimal listPrice, Integer quantity) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantity = quantity;
    }
    
    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.productID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        return true;
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

    public void setProductID(String productID) {
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
        return productID + " " + name + " " + category + " " + listPrice + " " + quantity;
    }

    
    
}
