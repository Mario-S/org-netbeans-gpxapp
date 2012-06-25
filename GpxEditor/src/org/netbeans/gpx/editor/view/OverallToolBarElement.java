/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;

/**
 *
 * @author msc
 */
public class OverallToolBarElement extends AbstractToolBarElement {

    public OverallToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
    }

    @Override
    protected AbstractGpxView getView(InnerPanelFactory factory) {
        return new OverallView(factory);
    }

}
