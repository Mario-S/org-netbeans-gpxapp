/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.gpx.binding.converter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class XMLGregorianCalendarConverterTest {
    
    private static final String DATE = "2011-06-03T13:00:53.000Z";
    


    /**
     * Test of convertForward method, of class XMLGregorianCalendarConverter.
     */
    @Test
    public void testConvertForward() throws DatatypeConfigurationException {

        DatatypeFactory factory = DatatypeFactory.newInstance();
        XMLGregorianCalendar cal = factory.newXMLGregorianCalendar(DATE);
        XMLGregorianCalendarConverter instance = new XMLGregorianCalendarConverter();
        String result = instance.convertForward(cal);
        assertEquals(DATE, result);
    }

    /**
     * Test of convertReverse method, of class XMLGregorianCalendarConverter.
     */
    @Test
    public void testConvertReverse() {

        
        XMLGregorianCalendarConverter instance = new XMLGregorianCalendarConverter();
        XMLGregorianCalendar result = instance.convertReverse(DATE);
        assertNotNull(result);
        assertEquals(2011, result.getYear());
        assertEquals(6, result.getMonth());
        assertEquals(3, result.getDay());
    }
}
