package org.netbeans.gpx.model.xam;

import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class GpxDocumentComponentFactoryImpl implements GpxDocumentComponentFactory {

    private GpxDocumentModelImpl model;
    
    private ThreadLocal<GpxVisitorImpl> holder;

    public GpxDocumentComponentFactoryImpl(GpxDocumentModelImpl model) {
        this.model = model;
        holder = new ThreadLocal<GpxVisitorImpl>();
    }
    
    @Override
    public GpxDocumentComponent createComponent(Element element, GpxDocumentComponent context) {
        GpxVisitorImpl visitor = getVisitor();
        return visitor.create(context, element);
    }
    
    GpxVisitorImpl getVisitor(){
        GpxVisitorImpl visitor = holder.get();
        
        if(visitor == null){
            visitor = new GpxVisitorImpl(model);
            holder.set(visitor);
        }
        
        visitor.init();
        
        return visitor;
    }

    @Override
    public GpxType createGpx() {
        return new Gpx(model);
    }

    @Override
    public MetadataType createMetadata() {
        return new Metadata(model);
    }

    @Override
    public TrackType createTrack() {
        return new Track(model);
    }

    @Override
    public PersonType createPerson() {
        return new Person(model);
    }

    @Override
    public CopyrightType createCopyright() {
        return new Copyright(model);
    }

    @Override
    public LinkType createLink() {
        return new Link(model);
    }

    @Override
    public EmailType createEmail() {
        return new Email(model);
    }

    @Override
    public BoundsType createBounds() {
        return new Bounds(model);
    }

    @Override
    public TrackSegmentType createTrackSegment() {
        return new TrackSegment(model);
    }

    @Override
    public WaypointType createWaypoint() {
        return new Waypoint(model);
    }

    @Override
    public Name createName() {
        return new Name(model);
    }

    @Override
    public Description createDescription() {
        return new Description(model);
    }

    @Override
    public Comment createComment() {
        return new Comment(model);
    }

    @Override
    public Source createSource() {
        return new Source(model);
    }

    @Override
    public Number createNumber() {
        return new Number(model);
    }

    @Override
    public Type createtType() {
        return new Type(model);
    }

    @Override
    public Text createText() {
        return new Text(model);
    }

    @Override
    public Year createYear() {
        return new Year(model);
    }

    @Override
    public License createLicense() {
        return new License(model);
    }

    @Override
    public Elevation createElevation() {
        return new Elevation(model);
    }

    @Override
    public Time createTime() {
        return new Time(model);
    }

    @Override
    public Magvar createMagvar() {
        return new Magvar(model);
    }

    @Override
    public Symbol createSymbol() {
        return new Symbol(model);
    }

    @Override
    public Satellite createSatellite() {
        return new Satellite(model);
    }

    @Override
    public Hdop createHdop() {
        return new Hdop(model);
    }

    @Override
    public Vdop createVdop() {
        return new Vdop(model);
    }

    @Override
    public Pdop createPdop() {
        return new Pdop(model);
    }

    @Override
    public AgeOfDGpsData createAgeOfDGpsData() {
        return new AgeOfDGpsData(model);
    }

    @Override
    public DGpsId createDGpsId() {
        return new DGpsId(model);
    }
}
