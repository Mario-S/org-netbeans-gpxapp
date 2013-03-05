package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Name extends ValueTextComponent{

    public Name(GpxDocumentModelImpl model) {
        super(model, SchemaType.NAME);
    }

    public Name(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
