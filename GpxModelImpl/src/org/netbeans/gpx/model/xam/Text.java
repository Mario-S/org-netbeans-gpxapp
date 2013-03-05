package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Text extends ValueTextComponent{

    public Text(GpxDocumentModelImpl model) {
        super(model, SchemaType.TEXT);
    }

    public Text(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
