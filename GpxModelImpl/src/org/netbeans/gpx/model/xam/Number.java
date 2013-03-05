package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Number extends ValueTextComponent{

    public Number(GpxDocumentModelImpl model) {
        super(model, SchemaType.NUMBER);
    }
    
    public Number(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
