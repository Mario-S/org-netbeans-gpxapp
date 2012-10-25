package org.netbeans.gpx.model.api;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Implementation of position with unmodifiable values.
 * @author msc
 */
final class ImmutablePosition implements Position {

    private final BigDecimal elevation;

    private final BigDecimal latitude;

    private final BigDecimal longitude;

    private final XMLGregorianCalendar time;
    
    public ImmutablePosition(double lat, double lon){
        this(new BigDecimal(lat), new BigDecimal(lon));
    }

    public ImmutablePosition(BigDecimal latitude, BigDecimal longitude) {
        this(latitude, longitude, null);
    }

    public ImmutablePosition(BigDecimal latitude, BigDecimal longitude, BigDecimal elevation) {
        this(latitude, longitude, elevation, null);
    }

    public ImmutablePosition(BigDecimal latitude, BigDecimal longitude, BigDecimal elevation, XMLGregorianCalendar time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.time = time;
    }

    @Override
    public BigDecimal getElevation() {
        return elevation;
    }

    @Override
    public BigDecimal getLatitude() {
        return latitude;
    }

    @Override
    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public XMLGregorianCalendar getTime() {
        return time;
    }
}
