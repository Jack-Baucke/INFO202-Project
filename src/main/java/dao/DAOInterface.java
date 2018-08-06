/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author bauja773
 */
public interface DAOInterface {

    void deleteProduct(Product product);

    Collection<String> getCategories();

    Collection<Product> getProducts();

    void saveCategory(String category);

    //have saveProduct and saveCategory in the same method!!
    void saveProduct(Product product);

    Product search(String id);
    
}
