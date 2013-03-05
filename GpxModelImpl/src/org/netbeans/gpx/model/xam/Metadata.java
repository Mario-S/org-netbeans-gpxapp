package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Metadata extends AbstractGpxComponent implements MetadataType {
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.METADATA;

    public Metadata(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }

    public Metadata(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return MetadataType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public CopyrightType getCopyright() {

        return getChild(CopyrightType.class);
    }

    @Override
    public void setCopyright(CopyrightType copyright) {

        removePreviousChild(getCopyright());

        addBefore(copyright);
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
    public Description getDesc() {
        return getChild(Description.class);
    }

    @Override
    public void setDesc(Description description) {
        removePreviousChild(getDesc());

        addBefore(description);
    }

    @Override
    public PersonType getAuthor() {
        return getChild(PersonType.class);
    }

    @Override
    public void setAuthor(PersonType person) {
        removePreviousChild(getAuthor());

        addBefore(person);
    }

    @Override
    public LinkType getLink() {
        return getChild(LinkType.class);
    }

    @Override
    public void setLink(LinkType link) {
        removePreviousChild(getLink());

        addBefore(link);
    }

    @Override
    public KeyWords getKeyWords() {
        return getChild(KeyWords.class);
    }

    @Override
    public void setKeyWords(KeyWords keyWords) {
        removePreviousChild(getKeyWords());

        addBefore(keyWords);
    }

    @Override
    public Time getTime() {
        return getChild(Time.class);
    }

    @Override
    public void setTime(Time time) {
        removePreviousChild(getTime());
        
        addBefore(time);
    }

    @Override
    public BoundsType getBounds() {
        return getChild(BoundsType.class);
    }

    @Override
    public void setBounds(BoundsType bounds) {
        removePreviousChild(getBounds());

        addBefore(bounds);
    }
}
