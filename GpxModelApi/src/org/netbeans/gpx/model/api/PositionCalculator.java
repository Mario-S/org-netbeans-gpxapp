package org.netbeans.gpx.model.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticMeasurement;
import org.gavaghan.geodesy.GlobalPosition;

/**
 * Calculator for spatial data between {@link Position}s. It is based on the Ellipsoid WGS84
 *
 * @author msc
 */
public enum PositionCalculator {
    
    Instance;
    
    private GeodeticCalculator geodeticCalculator;

    /*the ellipsoid is always WGS 84 for GPS data*/
    private static final Ellipsoid REF = Ellipsoid.WGS84;
    
    private PositionCalculator() {
        geodeticCalculator = new GeodeticCalculator();
    }

    /**
     * Calculates the sum of the distances between the positions.
     *
     * @param positions a collection of positions.
     * @return the total distance
     */
    public double getTotal(List<? extends Position> positions) {
        double total = 0.0;
        
        if (!positions.isEmpty()) {
            Iterator<? extends Position> it = positions.iterator();
            
            Position previous = it.next();
            
            while (it.hasNext()) {
                Position next = it.next();
                total = total + getDistance(previous, next);
            }
        }
        
        return total;
    }

    /**
     * Calculates the distance between two positions.
     *
     * @param from the start position
     * @param to the end position
     * @return distanc
     */
    public double getDistance(Position from, Position to) {
        
        GlobalPosition fromPosition = buildPosition(from);
        GlobalPosition toPosition = buildPosition(to);
        
        GeodeticMeasurement measurement = geodeticCalculator.calculateGeodeticMeasurement(REF, fromPosition, toPosition);
        return measurement.getPointToPointDistance();
    }
    
    private GlobalPosition buildPosition(Position from) {
        
        double lat = from.getLatitude().doubleValue();
        double lon = from.getLongitude().doubleValue();
        double ele = from.getElevation().doubleValue();
        return new GlobalPosition(lat, lon, ele);
    }

    /**
     * Calculates the centroid for the given positions.<br/> The method return a position with onlylatitude and
     * longitude set.
     *
     * @param positions collection of positions.
     * @return the centroid in a 2D plane.
     */
    public Position getCentroid(Collection<? extends Position> positions) {
        int size = positions.size();
        double sLat = 0, sLon = 0;
        for (Position position : positions) {
            sLat += position.getLatitude().doubleValue();
            sLon += position.getLongitude().doubleValue();
        }
        double lat = sLat / size, lon = sLon / size;
        return new ImmutablePosition(lat, lon);
    }
}
