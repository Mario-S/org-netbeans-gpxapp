package com.topografix.gpx.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class LinkTest {
    
    @Test
    public void testCopy(){
        Link original = new Link("web", "url", "web.de");
        Link copy = new Link(original);
        assertTrue(copy.hasContent());
    }
    
    /**
     * Test of hasContent method, of class Link.
     */
    @Test
    public void testHasContent() {
        
        Link instance = new Link();
        assertFalse(instance.hasContent());
        
        instance.setText("");
        assertFalse(instance.hasContent());
        
        instance.setType("Ex");
        assertTrue(instance.hasContent());
    }
}
