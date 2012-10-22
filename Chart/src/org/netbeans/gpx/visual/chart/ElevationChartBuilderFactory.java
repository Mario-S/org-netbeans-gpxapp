package org.netbeans.gpx.visual.chart;

/**
 *
 * @author msc
 */
public final class ElevationChartBuilderFactory {
    
    public static AbstractChartBuilder getChartBuilder(ChartType type){
	
	AbstractChartBuilder chartBuilder;
	if(type == ChartType.TIME){
	    chartBuilder = new TimeElevationChartBuilder();
	}else{
	    chartBuilder = new DistanceElevationChartBuilder();
	}
	return chartBuilder;
    }
}
