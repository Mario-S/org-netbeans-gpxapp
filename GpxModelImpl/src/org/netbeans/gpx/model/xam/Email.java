package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Email extends AbstractGpxComponent implements EmailType{
    
    private static final String ID = "id";
    
    private static final String DOMAIN = "domain";
    
    private Attribute idAttribute = new PrefixAttribute(ID);
    
    private Attribute domainAttribute = new PrefixAttribute(DOMAIN);
    
    private static final SchemaType SCHEMA_TYPE =  SchemaType.EMAIL;
    
    public Email(GpxDocumentModelImpl model){
        super(model, SCHEMA_TYPE);
    }

    public Email(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return EmailType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getId() {
        return getAttribute(idAttribute);
    }

    @Override
    public void setId(String id) {
        setAttribute(ID, idAttribute, id);
    }

    @Override
    public String getDomain() {
        return getAttribute(domainAttribute);
    }

    @Override
    public void setDomain(String domain) {
        setAttribute(DOMAIN, domainAttribute, domain);
    }
    
}
