package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class KeyWords extends ValueTextComponent{

    public KeyWords(GpxDocumentModelImpl model) {
        super(model, SchemaType.KEYWORDS);
    }
    
    public KeyWords(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
