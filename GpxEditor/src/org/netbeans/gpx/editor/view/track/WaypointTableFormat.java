/*
 * $$LastChangedRevision: 1 $$
 * Created on 13.08.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 * (C) 2004-2011 Copyright Dilax Intelcom GmbH. All Rights Reserved.
 */
package org.netbeans.gpx.editor.view.track;

import ca.odell.glazedlists.gui.TableFormat;
import com.topografix.gpx.model.Waypoint;

/**
 *
 * @author msc
 */
class WaypointTableFormat implements TableFormat<Waypoint> {

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int i) {

        String name;

        switch (i) {
            case 0:
                name = "Time";
                break;
            case 1:
                name = "Longitude";
                break;
            case 2:
                name = "Latitude";
                break;
            case 3:
                name = "Elevation";
                break;
            default:
                name = "";

        }

        return name;
    }

    @Override
    public Object getColumnValue(Waypoint e, int i) {

        Object vaue;

        switch (i) {
            case 0:
                vaue = e.getTime();
                break;
            case 1:
                vaue = e.getLon();
                break;
            case 2:
                vaue = e.getLat();
                break;
            case 3:
                vaue = e.getEle();
                break;
            default:
                vaue = "";

        }

        return vaue;

    }

}
