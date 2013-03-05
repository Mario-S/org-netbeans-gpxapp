package org.netbeans.gpx.model.xam;

import java.util.List;

/**
 *
 * @author msc
 */
public interface TrackType extends GpxDocumentComponent{
    
    Name getName();
    
    void setName(Name name);
    
    Comment getComment();
    
    void setComment(Comment cmt);
    
    Description getDescription();
    
    void setDescription(Description desc);
    
    Source getSource();
    
    void setSource(Source src);
    
    LinkType getLink();
    
    void setLink(LinkType link);
    
    Number getNumber();
    
    void setNumber(Number number);
    
    Type getType();
    
    void setType(Type type);
    
    List<TrackSegmentType> getTrackSegments();
    
    void setTrackSegments(List<TrackSegmentType> trkSeg);
    
    void addTrackSegment(int index, TrackSegmentType trackSegment);
    
    void removeTrackSegment(TrackSegmentType trackSegment);
    
}
