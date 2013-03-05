package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class DGpsId extends ValueTextComponent{

    public DGpsId(GpxDocumentModelImpl model) {
        super(model, SchemaType.DGPSID);
    }
    
    public DGpsId(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
