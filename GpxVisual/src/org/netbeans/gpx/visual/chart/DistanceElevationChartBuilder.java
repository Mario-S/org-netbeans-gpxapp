package org.netbeans.gpx.visual.chart;

import java.util.Collection;
import java.util.Iterator;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticMeasurement;
import org.gavaghan.geodesy.GlobalPosition;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.netbeans.gpx.model.entity.Waypoint;

/**
 *
 * @author msc
 */
class DistanceElevationChartBuilder extends AbstractChartBuilder {

    /*the ellipsoid is always WGS 84 for GPX data*/
    private static final Ellipsoid REF = Ellipsoid.WGS84;

    private GeodeticCalculator geoCalculator;

    public DistanceElevationChartBuilder() {

	geoCalculator = new GeodeticCalculator();
    }

    @Override
    public JFreeChart buildChart(Collection<? extends Waypoint> points) {
	//TODO i18n using Bundle
	String xAxis = "Distance";
	String valueAxis = "Elevation";

	Iterator<? extends Waypoint> pointIterator = points.iterator();

	XYSeries series = buildSeries(pointIterator);

	XYSeriesCollection dataSet = new XYSeriesCollection(series);

	JFreeChart chart = ChartFactory.createXYLineChart(null, xAxis, valueAxis,
		dataSet, PlotOrientation.VERTICAL,
		true, true, false);

	return chart;
    }

    private XYSeries buildSeries(Iterator<? extends Waypoint> pointIterator) {
	XYSeries series = new XYSeries(seriesName);
	
	Waypoint previous = pointIterator.next(); //get the first
	double distance = 0.0;
	series.add(distance, previous.getElevation());
	
	while (pointIterator.hasNext()) {
	    Waypoint point = pointIterator.next();
	    distance = distance + getDistance(previous, point);
	    double ele = point.getElevation().doubleValue();
	    series.add(distance, ele);
	    previous = point;
	}
	return series;
    }

    private double getDistance(Waypoint from, Waypoint to) {

	GlobalPosition fromPosition = buildPosition(from);
	GlobalPosition toPosition = buildPosition(to);

	GeodeticMeasurement measurement = geoCalculator.calculateGeodeticMeasurement(REF, fromPosition, toPosition);
	return measurement.getPointToPointDistance();
    }

    private GlobalPosition buildPosition(Waypoint from) {

	double lat = from.getLat().doubleValue();
	double lon = from.getLon().doubleValue();
	double ele = from.getElevation().doubleValue();
	return new GlobalPosition(lat, lon, ele);
    }
}
