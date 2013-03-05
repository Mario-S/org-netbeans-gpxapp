package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Hdop extends ValueTextComponent{

    public Hdop(GpxDocumentModelImpl model) {
        super(model, SchemaType.HDOP);
    }
    
    public Hdop(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
