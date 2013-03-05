package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Fix extends ValueTextComponent{

    public Fix(GpxDocumentModelImpl model) {
        super(model, SchemaType.FIX);
    }

    public Fix(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
