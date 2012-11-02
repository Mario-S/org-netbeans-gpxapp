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
    
    public PositionCalculatorTest() {
    }
    
    @Before
    public void setUp() {
        positions = new ArrayList<Position>();
        positions.add(new Point(1.0, 1.0));
        positions.add(new Point(1.0, 4.0));
        positions.add(new Point(4.0, 1.0));
        positions.add(new Point(4.0, 4.0));
    }

    /**
     * Test of getCentroid method, of class PositionCalculator.
     */
    @Test
    public void testGetCentroid() {
        PositionCalculator instance = new PositionCalculator();
        Position result = instance.getCentroid(positions);
        assertNotNull(result);
        double lat = result.getLatitude().doubleValue();
        double lon = result.getLongitude().doubleValue();
        assertEquals(lat, lon, 0.0);
        assertEquals(2.5, lat, 0.0);
    }
}
