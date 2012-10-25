package org.netbeans.gpx.model.api;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * Represents a geographical position with optional height and local time.
 * @author msc
 */
public interface Position {

    /**
     * Gets the value of the elevation property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    BigDecimal getElevation();

    /**
     * Gets the value of the latitude property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    BigDecimal getLatitude();

    /**
     * Gets the value of the longitude property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    BigDecimal getLongitude();

    /**
     * Gets the value of the time property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    XMLGregorianCalendar getTime();
    
}
