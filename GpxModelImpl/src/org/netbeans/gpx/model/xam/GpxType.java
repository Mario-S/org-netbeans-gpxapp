package org.netbeans.gpx.model.xam;

import java.util.List;

/**
 *
 * @author msc
 */
public interface GpxType extends GpxDocumentComponent{
    
    MetadataType getMetadata();
    
    void setMetadata(MetadataType metadata);
    
    List<TrackType> getTracks();
    
    void setTracks(List<TrackType> tracks);
    
    void addTrack(int index, TrackType track);
    
    void removeTrack(TrackType track);
}
