package org.netbeans.gpx.model.xam;

import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public interface GpxDocumentComponentFactory {
    
    GpxDocumentComponent createComponent(Element element, GpxDocumentComponent context);
    
    GpxType createGpx();
    MetadataType createMetadata();
    TrackType createTrack();
    PersonType createPerson();
    CopyrightType createCopyright();
    LinkType createLink();
    EmailType createEmail();
    BoundsType createBounds();
    TrackSegmentType createTrackSegment();
    WaypointType createWaypoint();
    
    Name createName();
    Description createDescription();
    Comment createComment();
    Source createSource();
    Number createNumber();
    Type createtType();
    Text createText();
    Year createYear();
    License createLicense();
    Elevation createElevation();
    Time createTime();
    Magvar createMagvar();
    Symbol createSymbol();
    Satellite createSatellite();
    ValueTextComponent createHdop();
    Vdop createVdop();
    Pdop createPdop();
    AgeOfDGpsData createAgeOfDGpsData();
    DGpsId createDGpsId();
}
