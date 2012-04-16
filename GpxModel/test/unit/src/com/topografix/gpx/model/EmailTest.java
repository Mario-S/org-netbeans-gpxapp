/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topografix.gpx.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class EmailTest {
   


    /**
     * Test of hasContent method, of class Email.
     */
    @Test
    public void testHasContent() {
        Email instance = new Email();
        assertFalse(instance.hasContent());
        instance.setId("me");
        assertTrue(instance.hasContent());
        instance.setDomain("web.de");
        assertTrue(instance.hasContent());
        
        instance = new Email();
        instance.setDomain("web.de");
        assertTrue(instance.hasContent());
    }
}
