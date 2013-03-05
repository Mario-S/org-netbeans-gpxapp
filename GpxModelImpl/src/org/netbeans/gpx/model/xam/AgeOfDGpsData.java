package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class AgeOfDGpsData extends ValueTextComponent{

    public AgeOfDGpsData(GpxDocumentModelImpl model) {
        super(model, SchemaType.AGEOFGPSDATA);
    }

    public AgeOfDGpsData(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }
    
}
