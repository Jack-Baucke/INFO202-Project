/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author bauja773
 */
public class DatabaseManager implements DAOInterface {

    private String url = "jdbc:h2:tcp://localhost:9058/project;IFEXISTS=TRUE";

    public DatabaseManager() {

    }

    public DatabaseManager(String url) {
        this.url = url;
    }

    @Override
    public void deleteProduct(Product product) {
        String sql = "delete from product where product_id = ?";

        try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, product.getProductID());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<String> getCategories() {
        String sql = "select distinct product_category from product order by product_category";

        try (
                // get a connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            ArrayList<String> categories = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productCategory = rs.getString("product_category");
                categories.add(productCategory);
            }

            return categories;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from product order by product_id";

        try (
                // get a connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            ArrayList<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productID = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String productDescription = rs.getString("product_description");
                String productCategory = rs.getString("product_category");
                BigDecimal listPrice = rs.getBigDecimal("product_price");
                Integer quantity = rs.getInt("product_quantity");

                // use the data to create a student object
                Product p = new Product(productID, productName, productDescription, productCategory, listPrice, quantity);

                // and put it in the collection
                products.add(p);
            }

            return products;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void saveProduct(Product product) {
        String sql = "merge into product (product_id, product_name, product_description, product_category, product_price, product_quantity) values (?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getListPrice());
            stmt.setInt(6, product.getQuantity());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Product search(String id) {
        String sql = "select * from product where product_id = ?";

        try (
                // get connection to database
                Connection connection = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, id);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String productID = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String productDescription = rs.getString("product_description");
                String productCategory = rs.getString("product_category");
                BigDecimal listPrice = rs.getBigDecimal("product_price");
                Integer quantity = rs.getInt("product_quantity");

                return new Product(productID, productName, productDescription, productCategory, listPrice, quantity);

            } else {
                // no student matches given ID so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<Product> filterByCategory(String category) {
        String sql = "select * from Product where product_category = ?";

        try (
                // get a connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            
            stmt.setString(1, category);
            
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            ArrayList<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productID = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String productDescription = rs.getString("product_description");

                BigDecimal listPrice = rs.getBigDecimal("product_price");
                Integer quantity = rs.getInt("product_quantity");

                // use the data to create a student object
                Product p = new Product(productID, productName, productDescription, category, listPrice, quantity);

                products.add(p);

            }

            return products;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new RuntimeException(ex);
        }

    }

}
