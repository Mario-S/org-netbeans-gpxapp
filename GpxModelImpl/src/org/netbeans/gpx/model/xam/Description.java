package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Description extends ValueTextComponent{

    public Description(GpxDocumentModelImpl model) {
        super(model, SchemaType.DESC);
    }
    
    public Description(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

}
