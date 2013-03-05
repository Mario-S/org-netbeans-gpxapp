package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Link extends AbstractGpxComponent implements LinkType{
    
    private static final String HREF = "href";
    
    private Attribute hrefAttribute = new PrefixAttribute(HREF);
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.LINK;

    public Link(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }

    public Link(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return LinkType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getHref() {
        return getAttribute(hrefAttribute);
    }

    @Override
    public void setHref(String href) {
        setAttribute(HREF, hrefAttribute, href);
    }

    @Override
    public Text getTextTag() {
        return getChild(Text.class);
    }

    @Override
    public void setTextTag(Text text) {
        removePreviousChild(getTextTag());
        
        addBefore(text);
    }

    @Override
    public Type getType() {
        return getChild(Type.class);
    }

    @Override
    public void setType(Type type) {
        removePreviousChild(getType());
        
        addBefore(type);
    }
    
}
