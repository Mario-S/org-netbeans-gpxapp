package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Source extends ValueTextComponent{

    public Source(GpxDocumentModelImpl model) {
        super(model, SchemaType.SRC);
    }
    
    public Source(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
