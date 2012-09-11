package org.netbeans.gpx.visual.chart;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.netbeans.gpx.model.entity.Waypoint;

/**
 *
 * @author msc
 */
class TimeElevationChartBuilder extends AbstractChartBuilder{

    @Override
    public JFreeChart buildChart(Collection<? extends Waypoint> points) {
	//TODO i18n using Bundle
	String timeAxis = "Time";
	String valueAxis = "Elevation";

	TimeSeries series = new TimeSeries(seriesName);
	for (Waypoint point : points) {
	    RegularTimePeriod period = buildTimePeriod(point);
	    double ele = point.getElevation().doubleValue();
	    series.add(period, ele);
	}

	TimeSeriesCollection dataSet = new TimeSeriesCollection(series);

	JFreeChart chart = ChartFactory.createTimeSeriesChart(null, timeAxis, valueAxis,
		dataSet, true, true, false);

	return chart;
    }

    private RegularTimePeriod buildTimePeriod(Waypoint point) {
	TimeZone timeZone = TimeZone.getDefault();
	Locale locale = Locale.getDefault();
	GregorianCalendar cal = point.getTime().
		toGregorianCalendar(timeZone, locale, null);
	//TODO select period based on time difference between the waypoints
	RegularTimePeriod period = new Second(cal.getTime());
	return period;
    }
    
}
