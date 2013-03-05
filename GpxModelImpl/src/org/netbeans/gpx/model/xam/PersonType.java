package org.netbeans.gpx.model.xam;

/**
 *
 * @author msc
 */
public interface PersonType extends GpxDocumentComponent{
    
    Name getName();
        
    void setName(Name name);
    
    EmailType getEmail();
    
    void setEmail(EmailType email);
    
    LinkType getLink();
    
    void setLink(LinkType link);
    
}
