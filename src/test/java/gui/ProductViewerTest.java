/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author bauja773
 */
public class ProductViewerTest {
    
    private DAO dao;
    private DialogFixture fixture;
    private Robot robot;
    
    private Product prod1;
    private Product prod2;
    
    public ProductViewerTest() {
        
    }
    
    @Before
    public void setUp() {
        
        robot = BasicRobot.robotWithNewAwtHierarchy();
        
        // set speed
        robot.settings().delayBetweenEvents(50);
                
        Collection<Product> products = new TreeSet<>();
        this.prod1 = new Product("id1", "name1", "desc1", "testCategory1",
        new BigDecimal("123.00"), 321);
        
        this.prod2 = new Product("id2", "name2", "desc2", "testCategory2",
        new BigDecimal("321.00"), 123);
        
        products.add(prod1);
        products.add(prod2);
        
        // create a mock for the DAO
        dao = mock(DAO.class);
        
        // stub the getCategories method to return the test categories
//        when(dao.getCategories()).thenReturn(categories);
        when(dao.getProducts()).thenReturn(products);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }
    
    @Test
    public void testView() {
        
        ProductReport dialog = new ProductReport(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts").target().getModel();
        model.updateItems(dao.getProducts());
//        fixture.list("listProducts").setModel(model);
        
        assertTrue("list contains prod1", model.contains(prod1));
        assertTrue("list contains prod2", model.contains(prod2));
        assertEquals("list contains correct no. products", 2, model.getSize());
        
    }
    
}
