/*
 * $$LastChangedRevision: 1 $$
 * Created on 13.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import java.awt.Image;
import org.netbeans.modules.xml.multiview.DesignMultiViewDesc;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.GpxToolBarElement;
import org.openide.util.ImageUtilities;

/**
 *
 * @author msc
 */
public class GeneralViewDesc extends DesignMultiViewDesc{
    
    private static final String NAME = "General";

    public GeneralViewDesc(GpxDataObject dObj) {
        super(dObj, NAME);
    }

    @Override
    public MultiViewElement createElement() {
        GpxDataObject dObj = (GpxDataObject) getDataObject();
        return new GpxToolBarElement(dObj);
    }

    @Override
    public Image getIcon() {
        return ImageUtilities.loadImage("org/netbeans/gpx/editor/resources/gpx.gif");
    }

    @Override
    public String preferredID() {
        return NAME; //TODO I18n
    }
    
}
