package org.netbeans.gpx.editor.view.track;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.view.AbstractGpxView;
import org.netbeans.gpx.editor.view.AbstractToolBarElement;
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
