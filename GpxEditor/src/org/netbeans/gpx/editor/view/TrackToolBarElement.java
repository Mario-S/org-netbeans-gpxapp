package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.track.TrackPanelFactory;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;

/**
 * Toolbar element for tracks.
 * @author msc
 */
public class TrackToolBarElement extends AbstractToolBarElement{
    
    private InnerPanelFactory factory;
    
    public TrackToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        
        factory = new TrackPanelFactory(toolbarEditor, gpxDataObject);
    }

    @Override
    protected AbstractGpxView createView() {
        return new TrackView(factory);
    }
    
}
