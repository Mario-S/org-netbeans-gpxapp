package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentModel;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Year extends ValueTextComponent{

    public Year(GpxDocumentModelImpl model) {
        super(model, SchemaType.YEAR);
    }
    
    public Year(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
