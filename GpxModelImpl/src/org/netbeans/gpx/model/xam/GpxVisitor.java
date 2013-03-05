package org.netbeans.gpx.model.xam;

/**
 *
 * @author msc
 */
public interface GpxVisitor {
 
    void visit(GpxType gpx);
    
    void visit(MetadataType metadata);
    
    void visit(CopyrightType copyright);
    
    void visit(EmailType email);
    
    void visit(LinkType link);
    
    void visit(PersonType person);
    
    void visit(BoundsType bounds);
    
    void visit(WaypointType wptType);
    
    void visit(TrackType trkType);
    
    void visit(TrackSegmentType trkSegType);
    
    void visit(ValueTextComponent textComponent);
}
