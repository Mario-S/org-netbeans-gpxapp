/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.model.api;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class SpatialCalculatorTest {
    
    private List<Position> positions;
    
    public SpatialCalculatorTest() {
    }
    
    @Before
    public void setUp() {
        positions = new ArrayList<Position>();
        positions.add(new ImmutablePosition(1.0, 1.0));
        positions.add(new ImmutablePosition(1.0, 4.0));
        positions.add(new ImmutablePosition(4.0, 1.0));
        positions.add(new ImmutablePosition(4.0, 4.0));
    }

    /**
     * Test of getCentroid method, of class SpatialCalculator.
     */
    @Test
    public void testGetCentroid() {
        SpatialCalculator instance = SpatialCalculator.Instance;
        Position result = instance.getCentroid(positions);
        assertNotNull(result);
        double lat = result.getLatitude().doubleValue();
        double lon = result.getLongitude().doubleValue();
        assertEquals(lat, lon, 0.0);
        assertEquals(2.5, lat, 0.0);
    }
}
