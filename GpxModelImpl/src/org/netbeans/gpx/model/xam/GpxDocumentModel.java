package org.netbeans.gpx.model.xam;

import org.netbeans.modules.xml.xam.dom.DocumentModel;

/**
 *
 * @author msc
 */
public interface GpxDocumentModel extends DocumentModel<GpxDocumentComponent> {
    
    GpxType getGpx();
    
    GpxDocumentComponentFactory getFactory();
}
