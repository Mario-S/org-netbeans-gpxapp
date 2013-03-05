package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class License extends ValueTextComponent{

    public License(GpxDocumentModelImpl model) {
        super(model, SchemaType.LICENSE);
    }
    
    public License(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
