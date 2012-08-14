package com.topografix.gpx.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class LinkTest {
    
    private Link instance;
    
    @Before
    public void setUp(){
        instance = new Link();
    }
    
    @Test
    public void testCopy(){
        Link original = new Link("web", "url", "web.de");
        Link copy = new Link(original);
        assertTrue(copy.hasContent());
        assertTrue(copy.equals(original));
    }
    
    /**
     * Test of hasContent method, of class Link.
     */
    @Test
    public void testHasContent() {
        
        assertFalse(instance.hasContent());
        
        instance.setText("");
        assertFalse(instance.hasContent());
        
        instance.setType("Ex");
        assertTrue(instance.hasContent());
    }
    
    @Test
    public void testEquals(){
        
        assertFalse(instance.equals(null));
        assertFalse(instance.hashCode() == 0);
        assertTrue(instance.equals(instance));
        
        Link other = new Link();
        assertTrue(instance.equals(other));
        assertTrue(instance.hashCode() == other.hashCode());
        
        other.setText("TEST");
        assertFalse(instance.equals(other));
        assertFalse(instance.hashCode() == other.hashCode());
    }

}
