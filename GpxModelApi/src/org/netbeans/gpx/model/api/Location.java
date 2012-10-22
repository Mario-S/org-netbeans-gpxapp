package org.netbeans.gpx.model.api;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * Interface that represent a 3 dimensional location on the geoid.
 * @author msc
 */
public interface Location {

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
