package org.netbeans.gpx.model.xam;

import java.util.List;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Gpx extends AbstractGpxComponent implements GpxType{
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.GPX;

    public Gpx(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }

    public Gpx(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return GpxType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MetadataType getMetadata() {
        
        return getChild(MetadataType.class);
    }

    @Override
    public void setMetadata(MetadataType metadata) {
        
        removePreviousChild(getMetadata());
        
        addBefore(metadata);
    }

    @Override
    public List<TrackType> getTracks() {
        return getChildren(TrackType.class);
    }

    @Override
    public void setTracks(List<TrackType> tracks) {
        
        List<TrackType> oldTracks = getTracks();
        for(TrackType trk : oldTracks){
            removePreviousChild(trk);
        }
        
        for(TrackType trk : tracks){
            addBefore(trk);
        }
    }

    @Override
    public void addTrack(int index, TrackType track) {
       getTracks().add(index, track);
    }

    @Override
    public void removeTrack(TrackType track) {
        removeChild(SchemaType.TRK, track);
    }
    
}
