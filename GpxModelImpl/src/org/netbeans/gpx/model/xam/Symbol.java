package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 * Text of GPS symbol name.
 * @author msc
 */
public class Symbol extends ValueTextComponent{

    public Symbol(GpxDocumentModelImpl model) {
        super(model, SchemaType.SYM);
    }

    public Symbol(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    } 
}
