package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel;
import org.netbeans.modules.xml.xam.dom.DocumentComponent;

/**
 *
 * @author msc
 */
public interface GpxDocumentComponent extends DocumentComponent<GpxDocumentComponent> {
    
    GpxModel.SchemaType getSchemaType();
    
    String getTagName();
    
    Class<? extends GpxDocumentComponent> getComponentyType();
    
    void accept(GpxVisitor visitor);
}
