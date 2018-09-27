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
import java.util.HashSet;
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
        robot.settings().delayBetweenEvents(300);
                
        Collection<Product> products = new HashSet<>();
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
       
        verify(dao).getProducts();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts").target().getModel();
        
        assertTrue("list contains prod1", model.contains(prod1));
        assertTrue("list contains prod2", model.contains(prod2));
        assertEquals("list contains correct no. products", 2, model.getSize());
        
    }
    
    @Test
    public void testEdit() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        fixture.list("listProducts").selectItem(prod1.toString());
        fixture.button("buttonEdit").click();
        
        DialogFixture editDialog = fixture.dialog("productEditor");
        
        editDialog.textBox("txtID").requireText("id1");
        editDialog.textBox("txtName").requireText("name1");
        editDialog.textBox("textAreaDescription").requireText("desc1");
        editDialog.comboBox("comboBoxCategory").requireSelection("testCategory1");
        editDialog.textBox("txtPrice").requireText("123.00");
        editDialog.textBox("txtQuantity").requireText("321");
    }
    
    @Test
    public void testSearch() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts").target().getModel();
        
        fixture.textBox("txtSearch").enterText("id2");
        fixture.button("buttonSearch").click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        
        verify(dao).search("id2"); 
        
        Product retrievedProduct = argument.getValue();        
        
        assertTrue("Search resulted in prod2", model.contains(retrievedProduct));
        assertEquals("Search only result in one product", 1, model.getSize());
    }
    
    @Test
    public void testFilter() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        fixture.comboBox("cmbCategory").selectItem("testCategory1");
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts").target().getModel();
        
        assertTrue("Search results in prod1", model.contains(prod1));
        assertEquals("Filter only results in one product", 1, model.getSize());
    }
    
    @Test
    public void testDelete() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        fixture.list("listProducts").selectItem(1);
        fixture.button("buttonDelete").click();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts").target().getModel();
        
        assertFalse("Prod1 was deleted", model.contains(prod1));
        assertEquals("List only contains 1 item now", 1, model.getSize());
        
    }
    
}
