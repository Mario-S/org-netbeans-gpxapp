/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.overall.OverallPanelFactory;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;

/**
 *
 * @author msc
 */
public class OverallToolBarElement extends AbstractToolBarElement {
    
    private InnerPanelFactory factory;

    public OverallToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        
        factory = new OverallPanelFactory(toolbarEditor, gpxDataObject);
    }

    @Override
    protected AbstractGpxView createView() {
        return new OverallView(factory);
    }

}
