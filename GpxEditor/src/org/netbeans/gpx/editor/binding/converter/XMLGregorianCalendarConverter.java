package org.netbeans.gpx.editor.binding.converter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;


/**
 *
 * @author msc
 */
public class XMLGregorianCalendarConverter extends Converter<XMLGregorianCalendar, String> {

    @Override
    public String convertForward(XMLGregorianCalendar cal) {

        String str = "";
        if (cal != null) {
            str = cal.toString();
        }
        return str;
    }

    @Override
    public XMLGregorianCalendar convertReverse(String value) {

        XMLGregorianCalendar cal = null;
        if (value != null) {
            try {
                DatatypeFactory factory = DatatypeFactory.newInstance();
                cal = factory.newXMLGregorianCalendar(value);
            } catch (DatatypeConfigurationException ex) {
                //TODO Log the exception
                
            }
        }
        return cal;
    }
}
