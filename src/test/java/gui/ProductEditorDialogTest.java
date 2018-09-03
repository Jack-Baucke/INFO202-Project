/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO;
import domain.Product;
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
public class ProductEditorDialogTest {
    
    private DAO dao;
    private DialogFixture fixture;
    private Robot robot;
    
    
    
    public ProductEditorDialogTest() {
    }
    
    @Before
    public void setUp() {
        
        robot = BasicRobot.robotWithNewAwtHierarchy();
        
        // set speed
        robot.settings().delayBetweenEvents(20);
        
        // add some majors for testing with
        Collection<String> categories = new TreeSet<>();
        categories.add("testCategory1");
        categories.add("testCategory2");
        
        // create a mock for the DAO
        dao = mock(DAO.class);
        
        // stub the getCategories method to return the test categories
        when(dao.getCategories()).thenReturn(categories);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testSave() {
        
        // create the dialog passing in the mocked DAO
        DataEntry dialog = new DataEntry(null, true, dao);
        
        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        
        // enter some details into the UI components
        fixture.textBox("txtID").enterText("testID");
        fixture.textBox("txtName").enterText("testName");
        fixture.textBox("textAreaDescription").enterText("testDescription");
        fixture.comboBox("comboBoxCategory").selectItem("testCategory1");
        fixture.textBox("txtPrice").enterText("1234.00");
        fixture.textBox("txtQuantity").enterText("4321");
        
        // click the save button
        fixture.button("buttonSave").click();
        
        // create a Mockito argument captor to use to retrieve
        // the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = 
                ArgumentCaptor.forClass(Product.class);
        
        // verify that the DAO.save method was called, and capture the passed product
        verify(dao).saveProduct(argument.capture());
        
        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();
        
        // test that the student's details were properly saved
        assertEquals("Ensure the ID was saved", "testID", savedProduct.getProductID());
        assertEquals("Ensure the name was saved", "testName", savedProduct.getName());
        assertEquals("Ensure the description was saved", "testDescription", savedProduct.getDescription());
        assertEquals("Ensure the category was saved", "testCategory1", savedProduct.getCategory());
        assertEquals("Ensure the price was saved", new BigDecimal("1234"), savedProduct.getListPrice());
        assertEquals("Ensure the quantity was saved", new Integer(4321), savedProduct.getQuantity());
    }
}
