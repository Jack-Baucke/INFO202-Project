/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.ArrayList;

/**
 *
 * @author bauja773
 */
public class DAO {
    
    private static ArrayList<Product> productList = new ArrayList<>();
    
    
    public void saveProduct(Product product) {
        productList.add(product);
    }
    
    public void getProducts() {
        for (Product product : productList) {
            product.toString();
        }
    }
    
}