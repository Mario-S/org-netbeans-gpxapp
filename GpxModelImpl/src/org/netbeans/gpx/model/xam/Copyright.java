package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Copyright extends AbstractGpxComponent implements CopyrightType{
    
    private static final String AUTHOR = "author";
    
    private Attribute authorAttribute = new PrefixAttribute(AUTHOR);
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.COPYRIGHT;

    public Copyright(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }

    public Copyright(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return CopyrightType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Year getYear() {
        return getChild(Year.class);
    }

    @Override
    public void setYear(Year year) {
        removePreviousChild(getYear());
        
        addBefore(year);
    }

    @Override
    public License getLicense() {
        return getChild(License.class);
    }

    @Override
    public void setLicense(License license) {
        removePreviousChild(getLicense());
        
        addBefore(license);
    }

    @Override
    public String getAuthor() {
        return getAttribute(authorAttribute);
    }

    @Override
    public void setAuthor(String author) {
        setAttribute(AUTHOR, authorAttribute, author);
    }
  
    
}
