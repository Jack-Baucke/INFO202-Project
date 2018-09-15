package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.DAO;
import dao.DAOInterface;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bauja773
 */
public class DAOTest {
    
    //private DAOInterface dao = new DAO();
    
    private DAOInterface dao = new DatabaseManager("jdbc:h2:tcp://localhost:9058/project-testing");
    
    private Product prodOne = new Product();
    private Product prodTwo = new Product();
    private Product prodThree = new Product();
    private Product prodFour = new Product();
    private Product prodEdit1 = new Product();
    
    @Test
    public void testDaoSave() {
        // save the prod using DAO
        dao.saveProduct(prodThree);
        
        //retrieve the same prod via DAO
        Product retrieved = dao.search("3");
        
        // ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same", 
                prodThree, retrieved);
    }
    
    @Test
    public void testDaoDelete() {
        // delete the product via the DAO
        dao.deleteProduct(prodOne);
        
        // try retrieve the deleted product
        Product retrieved = dao.search("1");
        
        // ensure that the student was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
    }
    
    @Test
    public void testDaoGetAll() {
        Collection<Product> products = dao.getProducts();
        
        // ensure the result includes the two saved products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
        
        // ensure the result ONLY includes the two saved products
        assertEquals("No extra products in result", 4, products.size());
        
        // find prodOne - result is not a map, so we have to scan for it
        for (Product p : products) {
            if (p.equals(prodOne)){
                
                // ensure that all the details were correctly retrieved
                assertEquals(prodOne.getProductID(), p.getProductID());
                assertEquals(prodOne.getName(), p.getName());
                assertEquals(prodOne.getDescription(), p.getDescription());
                assertEquals(prodOne.getCategory(), p.getCategory());
                assertEquals(prodOne.getListPrice(), p.getListPrice());
                assertEquals(prodOne.getQuantity(), p.getQuantity());                              
            }
        }
    }
    
    @Test
    public void testSearch() {        
        // get prodOne using seach method
        Product retrieved = dao.search("1");        
        // assert that you got back ProdOne, and not another product
        assertEquals(prodOne, retrieved);        
        // call getById (search?) using a non-existent ID
        Product fakeId = dao.search("fakeId");        
        // assert that the result is null
        assertEquals(fakeId, null);       
    }
    
    @Test
    public void testGetCategories() {
        Collection<String> categories = dao.getCategories();
        
        assertTrue("cat1 should exist", categories.contains("cat1"));
        assertTrue("cat2 should exist", categories.contains("cat2"));
        
        assertEquals("Only 2 categories in result", 3, categories.size());
        
        assertEquals("Prod1's cat is cat1", prodOne.getCategory(), "cat1");
        assertEquals("Prod2's cat is cat2", prodTwo.getCategory(), "cat2");
    }
    
    @Test
    public void testFilterByCategory() {
        
        Collection<Product> filteredProducts = dao.filterByCategory("cat2");
        
        assertTrue("prodTwo should be in list", filteredProducts.contains(prodTwo));
        assertTrue("prodFour should be in list", filteredProducts.contains(prodFour));
       
        
    }
    
    @Test
    public void testEdit() {
        

        prodEdit1.setName("testEdit");

        
        dao.saveProduct(prodEdit1);
        
        assertEquals("ProdEdit1's name has been edited", prodEdit1.getName(), "testEdit");
        
        
        
        
        
    }
    
    public DAOTest() {
    }
    
    @Before
    public void setUp() {
        this.prodOne = new Product("1", "name1", "desc1", "cat1", 
        new BigDecimal("11.00"), 22);
        
        this.prodTwo = new Product("2", "name2", "desc2", "cat2", 
        new BigDecimal("33.00"), 44);
        
        this.prodThree = new Product("3", "name3", "desc3", "cat3",
        new BigDecimal("55.00"), 66);
        
        this.prodFour = new Product("4", "name4", "desc4", "cat2",
        new BigDecimal("123.00"), 69);
        
        this.prodEdit1 = new Product("edit", "edit", "edit", "edit",
        new BigDecimal("7675.00"), 420);
        
        
        
        dao.saveProduct(prodOne);
        dao.saveProduct(prodTwo);
        dao.saveProduct(prodFour);
        dao.saveProduct(prodEdit1);


    }
    
    @After
    public void tearDown() {
        
        dao.deleteProduct(prodOne);
        dao.deleteProduct(prodTwo);
        dao.deleteProduct(prodThree);
        dao.deleteProduct(prodFour);
        dao.deleteProduct(prodEdit1);
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
