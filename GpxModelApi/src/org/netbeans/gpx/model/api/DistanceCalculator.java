package org.netbeans.gpx.model.api;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticMeasurement;
import org.gavaghan.geodesy.GlobalPosition;

/**
 * Calculator for distances between {@link Location}s.
 * It is based on the Ellipsoid WGS84
 *
 * @author msc
 */
public enum DistanceCalculator {
    
    Instance;
    
    private GeodeticCalculator geodeticCalculator;
    
    /*the ellipsoid is always WGS 84 for GPS data*/
    private static final Ellipsoid REF = Ellipsoid.WGS84;
    
    private DistanceCalculator(){
        geodeticCalculator = new GeodeticCalculator();
    }

    public double getDistance(Location from, Location to) {

        GlobalPosition fromPosition = buildPosition(from);
        GlobalPosition toPosition = buildPosition(to);

        GeodeticMeasurement measurement = geodeticCalculator.calculateGeodeticMeasurement(REF, fromPosition, toPosition);
        return measurement.getPointToPointDistance();
    }

    private GlobalPosition buildPosition(Location from) {

        double lat = from.getLatitude().doubleValue();
        double lon = from.getLongitude().doubleValue();
        double ele = from.getElevation().doubleValue();
        return new GlobalPosition(lat, lon, ele);
    }
}
