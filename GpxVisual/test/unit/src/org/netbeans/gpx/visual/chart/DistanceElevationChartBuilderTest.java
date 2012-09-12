/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.visual.chart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.gpx.model.entity.Waypoint;

/**
 *
 * @author msc
 */
public class DistanceElevationChartBuilderTest {

    private DistanceElevationChartBuilder classUnderTest;

    private List<Waypoint> points;

    public DistanceElevationChartBuilderTest() {
	classUnderTest = new DistanceElevationChartBuilder();
	buildPoints();
    }

    private void buildPoints() {
	points = new ArrayList<Waypoint>();
	
	Waypoint point = new Waypoint();
	point.setLat(BigDecimal.ZERO);
	point.setLon(BigDecimal.ZERO);
	point.setElevation(BigDecimal.ZERO);
	points.add(point);
	
	point = new Waypoint();
	point.setLat(BigDecimal.valueOf(5.0));
	point.setLon(BigDecimal.valueOf(5.0));
	point.setElevation(BigDecimal.valueOf(5.0));
	points.add(point);
	
	point = new Waypoint();
	point.setLat(BigDecimal.valueOf(10.0));
	point.setLon(BigDecimal.valueOf(10.0));
	point.setElevation(BigDecimal.valueOf(10.0));
	points.add(point);
    }

    /**
     * Test of buildChart method, of class TimeElevationChartBuilder.
     */
    @Test
    public void testBuildChart() {

	JFreeChart chart = classUnderTest.buildChart(points);
	assertNotNull(chart);
    }
    
}
