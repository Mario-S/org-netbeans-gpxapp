package org.netbeans.gpx.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticMeasurement;
import org.gavaghan.geodesy.GlobalPosition;
import org.netbeans.gpx.model.api.Position;
import org.netbeans.gpx.model.api.PositionCalculateable;
import org.netbeans.gpx.model.entity.Point;
import org.openide.util.lookup.ServiceProvider;

/**
 * Calculator for spatial data between {@link Position}s. It is based on the Ellipsoid WGS84
 *
 * @author msc
 */
@ServiceProvider(service=PositionCalculateable.class)
public class PositionCalculator implements PositionCalculateable{
    
    private GeodeticCalculator geodeticCalculator;

    /*the ellipsoid is always WGS 84 for GPS data*/
    private static final Ellipsoid REF = Ellipsoid.WGS84;
    
    public PositionCalculator() {
        geodeticCalculator = new GeodeticCalculator();
    }

    @Override
    public double getDistance(List<? extends Position> positions) {
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

    @Override
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

    @Override
    public Position getCentroid(Collection<? extends Position> positions) {
        int size = positions.size();
        BigDecimal sLat = new BigDecimal(0);
        BigDecimal sLon = new BigDecimal(0);
        for (Position position : positions) {
            sLat = sLat.add(position.getLatitude());
            sLon = sLon.add(position.getLongitude());
        }
        double lat = sLat.doubleValue() / size; 
        double lon = sLon.doubleValue() / size;
        return new Point(lat, lon);
    }
}
