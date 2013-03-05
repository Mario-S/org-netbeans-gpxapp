package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Comment extends ValueTextComponent{

    public Comment(GpxDocumentModelImpl model) {
        super(model, SchemaType.CMT);
    }

    public Comment(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
}
