package org.netbeans.gpx.model.xam;

import java.util.List;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Track extends AbstractGpxComponent implements TrackType{
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.TRK;

    public Track(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }
        
    public Track(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return TrackType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Name getName() {
        return getChild(Name.class);
    }

    @Override
    public void setName(Name name) {
        removePreviousChild(getName());
        
        addBefore(name);
    }

    @Override
    public Comment getComment() {
        return getChild(Comment.class);
    }

    @Override
    public void setComment(Comment cmt) {
        removePreviousChild(getComment());
        
        addBefore(cmt);
    }

    @Override
    public Description getDescription() {
        return getChild(Description.class);
    }

    @Override
    public void setDescription(Description desc) {
        removePreviousChild(getDescription());
        
        addBefore(desc);
    }

    @Override
    public Source getSource() {
        return getChild(Source.class);
    }

    @Override
    public void setSource(Source src) {
        removePreviousChild(getSource());
        
        addBefore(src);
    }

    @Override
    public LinkType getLink() {
        return getChild(LinkType.class);
    }

    @Override
    public void setLink(LinkType link) {
        removePreviousChild(getLink());
        
        addBefore(link);
    }

    @Override
    public Number getNumber() {
        return getChild(Number.class);
    }

    @Override
    public void setNumber(Number number) {
        removePreviousChild(getNumber());
        
        addBefore(number);
    }

    @Override
    public Type getType() {
        return getChild(Type.class);
    }

    @Override
    public void setType(Type type) {
        removePreviousChild(getType());
        
        addBefore(type);
    }

    @Override
    public List<TrackSegmentType> getTrackSegments() {
        return getChildren(TrackSegmentType.class);
    }

    @Override
    public void setTrackSegments(List<TrackSegmentType> trkSegs) {
        
        List<TrackSegmentType> previous = getTrackSegments();
        for(TrackSegmentType trkSeg : previous){
            removeTrackSegment(trkSeg);
        }
        
        for(TrackSegmentType trkSeg : trkSegs){
            addBefore(trkSeg);
        }
    }

    @Override
    public void addTrackSegment(int index, TrackSegmentType trackSegment) {
        getTrackSegments().add(index, trackSegment);
    }

    @Override
    public void removeTrackSegment(TrackSegmentType trackSegment) {
        removeChild(SchemaType.TRKSEG, trackSegment);
    }
}
