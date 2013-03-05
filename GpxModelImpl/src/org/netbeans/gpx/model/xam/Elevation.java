package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Elevation extends ValueTextComponent{

    public Elevation(GpxDocumentModelImpl model) {
        super(model, SchemaType.ELE);
    }
    
    public Elevation(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

}
