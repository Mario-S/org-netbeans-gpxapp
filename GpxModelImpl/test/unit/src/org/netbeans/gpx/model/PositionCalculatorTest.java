/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.gpx.model.api.Position;
import static org.junit.Assert.*;
import org.netbeans.gpx.model.entity.Point;

/**
 *
 * @author msc
 */
public class PositionCalculatorTest {
    
    private List<Position> positions;
    
    private PositionCalculator instance;
    
    public PositionCalculatorTest() {
        instance = new PositionCalculator();
    }
    
    @Before
    public void setUp() {
        positions = new ArrayList<Position>();
        positions.add(new Point(1.0, 1.0, 0.0));
        positions.add(new Point(1.0, 4.0, 0.0));
        positions.add(new Point(4.0, 1.0, 0.0));
        positions.add(new Point(4.0, 4.0, 0.0));
    }

    /**
     * Test of getCentroid method, of class PositionCalculator.
     */
    @Test
    public void testGetCentroid() {
        Position result = instance.getCentroid(positions);
        assertNotNull(result);
        double lat = result.getLatitude().doubleValue();
        double lon = result.getLongitude().doubleValue();
        assertEquals(lat, lon, 0.0);
        assertEquals(2.5, lat, 0.0);
    }
    
    @Test
    public void testGetDistance(){
        Position from = positions.get(0);
        Position to = new Point(1.0001, 1.0, 0.0);
        double result = instance.getDistance(from, to);
        assertEquals(11.0, result, 0.06);
        
        positions = new ArrayList<Position>();
        positions.add(from);
        positions.add(to);
        positions.add(from);
        
        result = instance.getDistance(positions);
        assertEquals(22.1, result, 0.02);
    }
}
