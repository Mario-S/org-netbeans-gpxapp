package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 * Number of satellites used to calculate the GPX fix.
 * @author msc
 */
public class Satellite extends ValueTextComponent{

    public Satellite(GpxDocumentModelImpl model) {
        super(model, SchemaType.SAT);
    }

    public Satellite(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
