package org.netbeans.gpx.model.xam;

import org.netbeans.modules.xml.xam.ComponentUpdater;

/**
 * This class is used to Synchronise DocumentObject and Model.
 * 
 * @author msc
 */
public class SyncUpdateVisitor implements ComponentUpdater<GpxDocumentComponent>, GpxVisitor {
    
    private GpxDocumentComponent parent;
    
    private int index;
    
    private Operation operation;

    @Override
    public void update(GpxDocumentComponent target, GpxDocumentComponent child, Operation oprtn) {
        update(target, child, -1, oprtn);
    }

    @Override
    public void update(GpxDocumentComponent target, GpxDocumentComponent child, int index, Operation oprtn) {
        
        if (target != null && child != null && validOperation(oprtn)){
            
            this.index = index;
            this.operation = oprtn;
            this.parent = target;
            child.accept(this);
        }
    }
    

    @Override
    public void visit(GpxType gpx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(MetadataType metadata) {
        
        if(parent instanceof GpxType){
            GpxType gpxType = (GpxType) parent;
            if(isAdd()){
                gpxType.setMetadata(metadata);
            }else if(isRemove()){
                gpxType.setMetadata(null);
            }
        }
    }

    @Override
    public void visit(CopyrightType copyright) {
        
        if(parent instanceof MetadataType){
            MetadataType metadata = (MetadataType) parent;
            if(isAdd()){
                metadata.setCopyright(copyright);
            }else if(isRemove()){
                metadata.setCopyright(null);
            }
        }
    }

    @Override
    public void visit(EmailType email) {
        if(parent instanceof PersonType){
            PersonType person = (PersonType) parent;
            if(isAdd()){
                person.setEmail(email);
            }else if(isRemove()){
                person.setEmail(null);
            }
        }
    }

    @Override
    public void visit(LinkType link) {
        if(parent instanceof MetadataType){
            MetadataType metadata = (MetadataType) parent;
            if(isAdd()){
                metadata.setLink(link);
            }else if(isRemove()){
                metadata.setLink(null);
            }
        }else if(parent instanceof PersonType){
            PersonType person = (PersonType) parent;
            if(isAdd()){
                person.setLink(link);
            }else if(isRemove()){
                person.setLink(null);
            }
        }
    }

    @Override
    public void visit(PersonType person) {
        
        if(parent instanceof MetadataType){
            MetadataType metadata = (MetadataType) parent;
            if(isAdd()){
                metadata.setAuthor(person);
            }else if(isRemove()){
                metadata.setAuthor(null);
            }
        }
    }

    @Override
    public void visit(BoundsType bounds) {
        
        if(parent instanceof MetadataType){
            MetadataType metadata = (MetadataType) parent;
            if(isAdd()){
                metadata.setBounds(bounds);
            }else if(isRemove()){
                metadata.setBounds(null);
            }
        }
    }

    @Override
    public void visit(WaypointType wptType) {
        
        
        if(parent instanceof TrackSegmentType){
            TrackSegmentType trackSegment = (TrackSegmentType) parent;
            if(isAdd()){
                trackSegment.addTrackPoint(index, wptType);
            }else if(isRemove()){
                trackSegment.removeTrackPoint(wptType);
            }
        }else if(parent instanceof GpxType){
            //TODO implement
            throw new UnsupportedOperationException("no yet");
        }
    }

    @Override
    public void visit(TrackType track) {
        
        if(parent instanceof GpxType){
            GpxType gpx = (GpxType) parent;
            if(isAdd()){
                gpx.addTrack(index, track);
            }else if(isRemove()){
                gpx.removeTrack(track);
            }
        }
    }

    @Override
    public void visit(TrackSegmentType trkSegType) {
        
        if(parent instanceof TrackType){
            TrackType track = (TrackType) parent;
            if(isAdd()){
                track.addTrackSegment(index, trkSegType);
            }else if(isRemove()){
                track.removeTrackSegment(trkSegType);
            }
        }
    }

    @Override
    public void visit(ValueTextComponent textComponent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean validOperation(Operation oprtn) {
        return oprtn == null || oprtn == Operation.ADD || oprtn == Operation.REMOVE;
    }
    
    private boolean isAdd(){
        return operation == Operation.ADD;
    }
    
    private boolean isRemove(){
        return operation == Operation.REMOVE;
    }
    
}
