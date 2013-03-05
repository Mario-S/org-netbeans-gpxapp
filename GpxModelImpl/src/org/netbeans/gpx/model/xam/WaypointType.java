package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.api.Position;

/**
 *
 * @author msc
 */
public interface WaypointType extends GpxDocumentComponent, Position{
    
    double getLat();
    
    void setLat(double lat);
    
    double getLon();
    
    void setLon(double lon);
    
    Elevation getEle();
    
    void setEle(Elevation ele);
    
    Time getTimeTag();
    
    void setTime(Time time);
    
    Magvar getMagvar();
    
    void setMagvar(Magvar magvar);
    
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
    
    Symbol getSymbol();
    
    void setSymbol(Symbol sym);
    
    Type getType();
    
    void setType(Type type);
    
    Satellite getSatellites();
    
    void setSatellites(Satellite sat);
    
    Hdop getHdop();
    
    void setHdop(Hdop hdop);
    
    Vdop getVdop();
    
    void setVdop(Vdop vdop);
    
    Pdop getPdop();
    
    void setPdop(Pdop pdop);
    
    AgeOfDGpsData getAgeOfDGpsData();
    
    void setAgeOfDGpsData(AgeOfDGpsData ageOfDGpsData);
    
    DGpsId getDgpsId();
    
    void setDGpsId(DGpsId dgpsId);
    
}
