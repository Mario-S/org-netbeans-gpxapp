package org.netbeans.gpx.model.xam;

/**
 *
 * @author msc
 */
public interface BoundsType extends GpxDocumentComponent{
    
    double getMinLat();
    
    void setMinLat(double minLat);
    
    double getMinLon();
    
    void setMinLon(double minLon);
    
    double getMaxLat();
    
    void setMaxLat(double maxLat);
    
    double getMaxLon();
    
    void setMaxLon(double maxLon);
    
}
