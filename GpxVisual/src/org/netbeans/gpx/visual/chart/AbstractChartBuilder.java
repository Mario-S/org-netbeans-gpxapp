package org.netbeans.gpx.visual.chart;

import java.util.Collection;
import org.jfree.chart.JFreeChart;
import org.netbeans.gpx.model.entity.Waypoint;

/**
 *
 * @author msc
 */
public abstract class AbstractChartBuilder {
    protected static final String seriesName = "Way Points";
    
    public abstract JFreeChart buildChart(Collection<? extends Waypoint> points);
}
