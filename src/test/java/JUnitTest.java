/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bauja773
 */
public class JUnitTest {
    
    static Integer result = 10;
    
    @Test
    public void testAddition() {        
        result += 10;
        assertEquals(new Integer(20), result);        
    }
    
    @Test
    public void testSubtraction() {
        result -= 10;
        assertEquals(new Integer(0), result);
    }
    
    public JUnitTest() {
    }
    
    @Before
    public void setUp() {
        result = 10;
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
