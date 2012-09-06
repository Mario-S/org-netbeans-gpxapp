package org.netbeans.gpx.model.entity;

import org.netbeans.gpx.model.entity.Link;
import org.netbeans.gpx.model.entity.Person;
import org.netbeans.gpx.model.entity.Email;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class PersonTest {
   

    /**
     * Test of hasContent method, of class Person.
     */
    @Test
    public void testHasContent() {
        Person instance = new Person();
        assertFalse(instance.hasContent());
        
        Email email = new Email();
        instance.setEmail(email);
        assertFalse(instance.hasContent());
        
        Link link = new Link();
        instance.setLink(link);
        assertFalse(instance.hasContent());
        
        email.setId("");
        assertFalse(instance.hasContent());
        
        email.setId("me");
        assertTrue(instance.hasContent());
    }
}
