package org.netbeans.gpx.visual;

import java.awt.BorderLayout;
import org.netbeans.gpx.model.Selection;
import org.netbeans.gpx.model.entity.Waypoint;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays elevation profile.
 */
@ConvertAsProperties(
    dtd = "-//org.netbeans.gpx.visual//Elevation//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "ElevationTopComponent",
iconBase = "org/netbeans/gpx/visual/resources/elevation.gif",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "navigator", openAtStartup = false)
@ActionID(category = "Window", id = "org.netbeans.gpx.visual.ElevationTopComponent")
@ActionReference(path = "Menu/Window/GPX" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_ElevationAction",
preferredID = "ElevationTopComponent")
@Messages({
    "CTL_ElevationAction=Elevation",
    "CTL_ElevationTopComponent=Elevation Window",
    "HINT_ElevationTopComponent=This is a Elevation window"
})
public final class ElevationTopComponent extends TopComponent implements LookupListener {

    private static final long serialVersionUID = -7900084742185260837L;

    private Lookup.Result<Waypoint> result;

    public ElevationTopComponent() {
        initComponents();
        setName(Bundle.CTL_ElevationTopComponent());
        setToolTipText(Bundle.HINT_ElevationTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        elevationPanel = new javax.swing.JPanel();

        elevationPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(elevationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(elevationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel elevationPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        result = Selection.INSTANCE.getLookup().lookupResult(Waypoint.class);
        result.allItems();
        result.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(this);
        result = null;
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Collection<? extends Waypoint> points = result.allInstances();

        if (!points.isEmpty()) {

            //TODO sort the collection based on time

            StringBuilder builder = new StringBuilder();
            for (Waypoint p : points) {
                builder.append("Elevation: ").append(p.getElevation()).append("\n");
            }

            Logger.getLogger(getClass().getName()).log(Level.INFO, builder.toString());

            ChartPanel chartPanel = new ChartPanel(buildChart(points));
            elevationPanel.add(chartPanel, BorderLayout.CENTER);
        }
    }

    private JFreeChart buildChart(Collection<? extends Waypoint> points) {

        //TODO i18n using Bundle
        String timeAxis = "Time";
        String valueAxis = "Elevation";
        String seriesName = "Way Points";

        TimeSeries series = new TimeSeries(seriesName);
        for (Waypoint point : points) {
            RegularTimePeriod period = buildTimePeriod(point);
            series.add(period, point.getElevation());
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
