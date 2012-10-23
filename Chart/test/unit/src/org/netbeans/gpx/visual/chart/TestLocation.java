package org.netbeans.gpx.visual.chart;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.gpx.model.api.Location;

/**
 *
 * @author msc
 */
public class TestLocation implements Location {

    private BigDecimal elevation;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private XMLGregorianCalendar time;

    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setTime(XMLGregorianCalendar time) {
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
