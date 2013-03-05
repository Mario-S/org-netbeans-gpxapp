package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Magvar extends ValueTextComponent{

    public Magvar(GpxDocumentModelImpl model) {
        super(model, SchemaType.MAGVAR);
    }

    public Magvar(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
}
