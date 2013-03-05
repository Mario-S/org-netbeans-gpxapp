package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Person extends AbstractGpxComponent implements PersonType{
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.AUTHOR;

    public Person(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }

    public Person(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return PersonType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Name getName() {
        return getChild(Name.class);
    }

    @Override
    public void setName(Name name) {
        removePreviousChild(getName());
        
        addBefore(name);
    }

    @Override
    public EmailType getEmail() {
        return getChild(EmailType.class);
    }

    @Override
    public void setEmail(EmailType email) {
        removePreviousChild(getEmail());
        
        addBefore(email);
    }

    @Override
    public LinkType getLink() {
        return getChild(LinkType.class);
    }

    @Override
    public void setLink(LinkType link) {
        removePreviousChild(getEmail());
        
        addBefore(link);
    }
    
    
}
