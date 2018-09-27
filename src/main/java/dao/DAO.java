/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.math.BigDecimal;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author bauja773
 */
public class DAO implements DAOInterface {

//    private static Collection<Product> productList = new HashSet<>();
//    private static Collection<String> categoryList = new HashSet<>();
    private static Map<String, Product> productIDMap = new HashMap<>();
    private static Multimap<String, Product> categoryMap = HashMultimap.create();

    //have saveProduct and saveCategory in the same method!!
    @Override
    public void saveProduct(Product product) {
//        productList.add(product);
//        categoryList.add(product.getCategory());
        productIDMap.put(product.getProductID(), product);
        categoryMap.put(product.getCategory(), product);
    }

    @Override
    public Collection<Product> getProducts() {
        SortedSet<String> keys = new TreeSet<String>(productIDMap.keySet());
        return productIDMap.values();
    }

    @Override
    public Collection<String> getCategories() {
        return categoryMap.keySet();
    }

    @Override
    public void deleteProduct(Product product) {
//        productList.remove(product);
        productIDMap.remove(product.getProductID());
        categoryMap.remove(product.getCategory(), product);
    }

    @Override
    public Product search(String id) {
        if (productIDMap.containsKey(id)) {
            return productIDMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Product> filterByCategory(String category) {
        Collection<Product> productList = categoryMap.get(category);
        return productList;
    }
}
