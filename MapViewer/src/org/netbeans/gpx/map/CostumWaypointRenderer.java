package org.netbeans.gpx.map;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

/**
 *
 * @author msc
 */
public class CostumWaypointRenderer implements WaypointRenderer{

    @Override
    public boolean paintWaypoint(Graphics2D gd, JXMapViewer jxmv, Waypoint wpnt) {
        gd.setColor(Color.RED);
        gd.drawLine(-4,-4,+4,+4);
        gd.drawLine(-4,+4,+4,-4);
        return true;
    }
    
}
