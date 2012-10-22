package org.netbeans.gpx.visual.chart;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.netbeans.gpx.model.api.Location;

/**
 *
 * @author msc
 */
class TimeElevationChartBuilder extends AbstractChartBuilder {
    private String timeAxis;

    public TimeElevationChartBuilder() {
	//TODO i18n
	timeAxis = "Time (h:m:s)";
    }

    @Override
    public JFreeChart buildChart(Collection<? extends Location> points) {

	Location firstWayPoint = null; 
	
	TimeSeries series = new TimeSeries(seriesName);
	for (Location point : points) {
	    if(firstWayPoint == null){
		firstWayPoint = point;
	    }
	    
	    RegularTimePeriod period = buildTimePeriod(point);
	    double ele = point.getElevation().doubleValue();
	    series.add(period, ele);
	}

	TimeSeriesCollection dataSet = new TimeSeriesCollection(series);

	JFreeChart chart = ChartFactory.createTimeSeriesChart(null, timeAxis, valueAxis,
		dataSet, true, true, false);

	formatDomainAxis(chart, firstWayPoint);

	return chart;
    }

    private RegularTimePeriod buildTimePeriod(Location point) {
	GregorianCalendar cal = getGregorianCalendar(point);
	//TODO select period based on time difference between the waypoints
	RegularTimePeriod period = new Second(cal.getTime());
	return period;
    }

    private GregorianCalendar getGregorianCalendar(Location point) {
	TimeZone timeZone = TimeZone.getDefault();
	Locale locale = Locale.getDefault();
	XMLGregorianCalendar cal = point.getTime();
	return cal.toGregorianCalendar(timeZone, locale, null);
    }

    private void formatDomainAxis(JFreeChart chart, Location firstWayPoint) {

	RegularTimePeriod first = new Minute(getGregorianCalendar(firstWayPoint).getTime());
	RelativeDateFormat relativedateformat = new RelativeDateFormat(first.getFirstMillisecond());
	relativedateformat.setSecondFormatter(new DecimalFormat("00"));
	
	XYPlot xyplot = (XYPlot) chart.getPlot();
	DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
	dateaxis.setDateFormatOverride(relativedateformat);
	dateaxis.setLowerMargin(0.0d);
    }
}
