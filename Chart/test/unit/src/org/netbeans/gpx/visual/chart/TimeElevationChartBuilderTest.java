package org.netbeans.gpx.visual.chart;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class TimeElevationChartBuilderTest {

    private TimeElevationChartBuilder classUnderTest;

    private List<TestLocation> points;

    public TimeElevationChartBuilderTest() {
        classUnderTest = new TimeElevationChartBuilder();
        buildPoints();
    }

    private void buildPoints() {
        points = new ArrayList<TestLocation>();

        TestLocation point = new TestLocation();
        XMLGregorianCalendar cal = new XMLGregorianCalendarImpl();
        cal.setTime(10, 0, 0);
        point.setTime(cal);
        point.setElevation(BigDecimal.ZERO);
        points.add(point);

        point = new TestLocation();
        cal = new XMLGregorianCalendarImpl();
        cal.setTime(10, 0, 2);
        point.setTime(cal);
        point.setElevation(BigDecimal.valueOf(5.0));
        points.add(point);

        point = new TestLocation();
        cal = new XMLGregorianCalendarImpl();
        cal.setTime(10, 0, 4);
        point.setTime(cal);
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

        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis axis = plot.getDomainAxis();
        assertTrue(axis instanceof DateAxis);
    }
}
