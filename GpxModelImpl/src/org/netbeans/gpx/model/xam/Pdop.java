package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Pdop extends ValueTextComponent{

    public Pdop(GpxDocumentModelImpl model) {
        super(model, SchemaType.PDOP);
    }
    
    public Pdop(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
