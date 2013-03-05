package org.netbeans.gpx.model.xam;

/**
 *
 * @author msc
 */
public interface CopyrightType extends GpxDocumentComponent {
    
    Year getYear();
    
    void setYear(Year year);

    License getLicense();
    
    void setLicense(License license);

    String getAuthor();
    
    void setAuthor(String author);
}
