package org.netbeans.gpx.visual.chart;

import java.util.Collection;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.netbeans.gpx.model.api.SpatialCalculator;
import org.netbeans.gpx.model.api.Position;

/**
 *
 * @author msc
 */
class DistanceElevationChartBuilder extends AbstractChartBuilder {

    private SpatialCalculator distanceCalculator;
    
    public DistanceElevationChartBuilder(){
        distanceCalculator = SpatialCalculator.Instance;
    }

    @Override
    public JFreeChart buildChart(Collection<? extends Position> points) {
	//TODO i18n using Bundle
	String xAxis = "Distance (m)";

	Iterator<? extends Position> pointIterator = points.iterator();

	XYSeries series = buildSeries(pointIterator);

	XYSeriesCollection dataSet = new XYSeriesCollection(series);

	JFreeChart chart = ChartFactory.createXYLineChart(null, xAxis, valueAxis,
		dataSet, PlotOrientation.VERTICAL,
		true, true, false);
	
	return chart;
    }

    private XYSeries buildSeries(Iterator<? extends Position> pointIterator) {
	XYSeries series = new XYSeries(seriesName);
	
	Position previous = pointIterator.next(); //get the first
	double distance = 0.0;
	series.add(distance, previous.getElevation());
	
	while (pointIterator.hasNext()) {
	    Position point = pointIterator.next();
	    distance = distance + getDistance(previous, point);
	    double ele = point.getElevation().doubleValue();
	    series.add(distance, ele);
	    previous = point;
	}
	return series;
    }

    private double getDistance(Position from, Position to) {
        return distanceCalculator.getDistance(from, to);
    }

   
}
