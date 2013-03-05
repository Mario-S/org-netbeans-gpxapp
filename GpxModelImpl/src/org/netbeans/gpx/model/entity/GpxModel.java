package org.netbeans.gpx.model.entity;

/**
 *
 * @author msc
 */
public interface GpxModel {

    enum Schema {

        NAME_SPACE("http://www.topografix.com/GPX/1/1"),
        LOCATION("http://www.topografix.com/GPX/1/1/gpx.xsd");

        private String value;

        private Schema(String val) {
            this.value = val;
        }

        @Override
        public String toString() {
            return value;
        }
    };

    /**
     * The values are based on the xsd types of a gpx file.
     */
    enum SchemaType {

        GPX, METADATA, PERSON, BOUNDS, LINK,
        COPYRIGHT, POINT, POINTSEQUENCE,
        TRK, TRKSEG, ROUTE,
        TRKPT, WPT,
        WAYPOINT, NAME, DESC, AUTHOR,
        TIME, KEYWORDS, EMAIL, 
        TEXT, TYPE, YEAR, LICENSE,
        CMT, SRC, NUMBER,
        ELE, MAGVAR, SYM,
        FIX, SAT, HDOP, VDOP, PDOP,
        AGEOFGPSDATA, DGPSID
    }

    /**
     * Defines a method that should check if the object's filed differ from the 
     * initialized state.
     * 
     * @return 
     */
    boolean hasContent();
}
