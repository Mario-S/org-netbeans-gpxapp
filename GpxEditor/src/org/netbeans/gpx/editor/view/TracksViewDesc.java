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
public class TracksViewDesc extends DesignMultiViewDesc{
    
    private static final String NAME = "Tracks";

    public TracksViewDesc(XmlMultiViewDataObject dObj) {
        super(dObj, NAME);
    }

    @Override
    public MultiViewElement createElement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image getIcon() {
        return ImageUtilities.loadImage("org/netbeans/gpx/editor/resources/track.gif");
    }

    @Override
    public String preferredID() {
        return NAME;
    }
    
}
