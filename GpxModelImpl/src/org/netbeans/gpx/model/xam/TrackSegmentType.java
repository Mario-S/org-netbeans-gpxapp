package org.netbeans.gpx.model.xam;

import java.util.List;

/**
 *
 * @author msc
 */
public interface TrackSegmentType extends GpxDocumentComponent{
    
    List<WaypointType> getTrackPoints();
    
    void setTrackPoints(List<WaypointType> trkpts);
    
    void addTrackPoint(int index, WaypointType waypoint);
    
    void removeTrackPoint(WaypointType waypoint);
}
