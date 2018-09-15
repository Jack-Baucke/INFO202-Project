/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bauja773
 */
public class CustomerDatabaseManager implements CustomerDAOInterface{
    
    private String url = "jdbc:h2:tcp://localhost:9058/project;IFEXISTS=TRUE";
    
    public CustomerDatabaseManager() {       
        
    }    
    
    public CustomerDatabaseManager(String url) {        
        this.url = url;
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customer (username, first_name, surname, password, credit_card, email, shipping_address) values(?,?,?,?,?,?,?)";
        
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getCreditCardDetails());
            stmt.setString(6, customer.getEmailAddress());
            stmt.setString(7, customer.getShippingAddress());      
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
            
        }
    }

    @Override
    public Customer getCustomer(String user) {
        String sql = "select * from customer where username = ?";
        
        try (
                
                Connection connection = JdbcConnection.getConnection(url);
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String personID = rs.getString("person_id");
                String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                String creditCardDetails = rs.getString("credit_card");
                String emailAddress = rs.getString("email");
                String shippingAddress = rs.getString("shipping_address");
                
                return new Customer(personID, username, firstName, surname, password, creditCardDetails, emailAddress, shippingAddress);
            
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        
        Customer customer = getCustomer(username);
        
        return (customer.getPassword().equals(password));

    }

}
