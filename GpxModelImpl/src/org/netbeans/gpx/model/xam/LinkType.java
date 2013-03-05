package org.netbeans.gpx.model.xam;

/**
 *
 * @author msc
 */
public interface LinkType extends GpxDocumentComponent{
    
    String getHref();
    
    void setHref(String href);
    
    Text getTextTag();
    
    void setTextTag(Text text);
    
    Type getType();
    
    void setType(Type type);
}
