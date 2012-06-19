package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.DesignMultiViewDesc;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataObject;

/**
 *
 * @author msc
 */
abstract class AbstractGpxViewDesc extends DesignMultiViewDesc{

    public AbstractGpxViewDesc(XmlMultiViewDataObject dObj, String name) {
        super(dObj, name);
    }
    
    @Override
    protected GpxDataObject getDataObject() {
        return (GpxDataObject) super.getDataObject();
    }
    
    
}
