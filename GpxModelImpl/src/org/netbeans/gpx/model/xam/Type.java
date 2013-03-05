package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Type extends ValueTextComponent{

    public Type(GpxDocumentModelImpl model) {
        super(model, SchemaType.TYPE);
    }
    
    public Type(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
