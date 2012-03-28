package org.netbeans.gpx.binding.converter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;
import org.openide.util.Exceptions;

/**
 *
 * @author msc
 */
public class XMLGregorianCalendarConverter extends Converter<XMLGregorianCalendar, String> {

    @Override
    public String convertForward(XMLGregorianCalendar cal) {
        return cal.toString();
    }

    @Override
    public XMLGregorianCalendar convertReverse(String value) {

        XMLGregorianCalendar cal = null;
        try {
            DatatypeFactory factory = DatatypeFactory.newInstance();
            cal = factory.newXMLGregorianCalendar(value);
        } catch (DatatypeConfigurationException ex) {
            Exceptions.printStackTrace(ex);
        }
        return cal;
    }
}
