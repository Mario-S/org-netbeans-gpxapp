package org.netbeans.gpx.model.xam;


/**
 *
 * @author msc
 */
public interface MetadataType extends GpxDocumentComponent{
    
    Name getName();
    
    void setName(Name name);
    
    Description getDesc();
        
    void setDesc(Description desc);
    
    PersonType getAuthor();
    
    void setAuthor(PersonType person);
    
    CopyrightType getCopyright();
    
    void setCopyright(CopyrightType copyright);
    
    LinkType getLink();
    
    void setLink(LinkType link);
    
    Time getTime();
    
    void setTime(Time time);
    
    KeyWords getKeyWords();
    
    void setKeyWords(KeyWords keyWords);
    
    BoundsType getBounds();
    
    void setBounds(BoundsType bounds);
        
}
