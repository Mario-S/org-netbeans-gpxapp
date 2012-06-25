package org.netbeans.gpx.editor.view;

import java.awt.Image;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.modules.xml.multiview.DesignMultiViewDesc;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataObject;
import org.openide.util.ImageUtilities;

/**
 *
 * @author msc
 */
public class TrackViewDesc extends AbstractGpxViewDesc{
    
    private static final String NAME = "Tracks";

    public TrackViewDesc(XmlMultiViewDataObject dObj) {
        super(dObj, NAME);
    }

    @Override
    public MultiViewElement createElement() {
        return new TrackToolBarElement(getDataObject());
    }

    @Override
    public Image getIcon() {
        return ImageUtilities.loadImage("org/netbeans/gpx/editor/resources/track.png");
    }

    @Override
    public String preferredID() {
        return NAME;
    }
    
}
