/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author bauja773
 */
public class DAO {
    
    private static Collection<Product> productList = new HashSet<>();
    private static Collection<String> categoryList = new HashSet<>();
    
    //have saveProduct and saveCategory in the same method!!
    public void saveProduct(Product product) {
        productList.add(product);
    }
    
    public Collection<Product> getProducts() {
        return productList;
    }
    
    public void saveCategory(String category) {
        categoryList.add(category);
    }
    
    public Collection<String> getCategories() {
        return categoryList;
    }
    
    public void deleteProduct(Product product) {
        productList.remove(product);
    }
    
}
