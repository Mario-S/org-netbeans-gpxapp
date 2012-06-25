package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.GpxPanelFactory;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;

/**
 * Toolbar element for tracks.
 * @author msc
 */
public class TrackToolBarElement extends AbstractToolBarElement{
    
    public TrackToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
    }

    @Override
    protected AbstractGpxView getView(InnerPanelFactory factory) {
        return new TrackView(factory);
    }
    
}
