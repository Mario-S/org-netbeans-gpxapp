package org.netbeans.gpx.model.xam;

import java.util.Collection;
import java.util.List;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.Model;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentModel;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author msc
 */
public class ValueTextComponent extends AbstractGpxComponent implements TextDocumentComponent{
    
    private SchemaType schemaType;

    public ValueTextComponent(GpxDocumentModelImpl model, SchemaType schemaType) {
        super(model, schemaType);
        this.schemaType = schemaType;
    }

    public ValueTextComponent(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) {
        setText(getTagName(), value);
    }
    
    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return TextDocumentComponent.class;
    }

    @Override
    public SchemaType getSchemaType() {
        return schemaType;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }
}
