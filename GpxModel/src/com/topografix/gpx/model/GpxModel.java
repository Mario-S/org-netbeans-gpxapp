package com.topografix.gpx.model;

/**
 *
 * @author msc
 */
public interface GpxModel {

    /**
     * The values are based on the xsd types of a gpx file.
     */
    enum SchemaType {

        GPX, METADATA, PERSON, BOUNDS, LINK,
        COPYRIGHT, POINT, POINTSEQUENCE,
        TRACK, TRACKSEGEMENT, ROUTE,
        WAYPOINT
    }

    /**
     * Defines a method that should check if the object's filed differ from the 
     * initialized state.
     * 
     * @return 
     */
    boolean hasContent();
}
