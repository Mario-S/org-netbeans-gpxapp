package org.netbeans.gpx.map;

import java.awt.BorderLayout;
import java.util.Collection;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.gpx.model.api.Position;
import org.netbeans.gpx.model.api.Selection;
import org.netbeans.gpx.model.api.PositionCalculator;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//org.netbeans.gpx.map//Map//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "MapTopComponent",
iconBase = "org/netbeans/gpx/map/google-map-icon.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.netbeans.gpx.map.MapTopComponent")
@ActionReference(path = "Menu/Window/GPX" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_MapAction",
preferredID = "MapTopComponent")
@Messages({
	"CTL_MapAction=Map",
	"CTL_MapTopComponent=Map Window",
	"HINT_MapTopComponent=This is a Map window"
})
public final class MapTopComponent extends TopComponent implements LookupListener{
    
	private JXMapKit mapKit;
    
    private Lookup.Result<Position> result;
    
    private Collection<? extends Position> points;

	public MapTopComponent() {
		
		initComponents();
		setName(Bundle.CTL_MapTopComponent());
		setToolTipText(Bundle.HINT_MapTopComponent());

        mapKit = new JXMapKit();
		mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapPanel.add(mapKit.getMainMap(), BorderLayout.CENTER);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapPanel = new javax.swing.JPanel();

        mapPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mapPanel;
    // End of variables declaration//GEN-END:variables
	@Override
	public void componentOpened() {
		result = Selection.Instance.getLookup().lookupResult(Position.class);
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
        points = result.allInstances();
	
        updateMap();
    }

    private void updateMap() {
        if(!points.isEmpty()){
            Position center = PositionCalculator.Instance.getCentroid(points);
            double lat = center.getLatitude().doubleValue();
            double lon = center.getLongitude().doubleValue();
            mapKit.setCenterPosition(new GeoPosition(lat, lon));
        }
    }
}
