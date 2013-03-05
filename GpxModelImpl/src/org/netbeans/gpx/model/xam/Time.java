package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Time extends ValueTextComponent{

    public Time(GpxDocumentModelImpl model) {
        super(model, SchemaType.TIME);
    }

    public Time(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
