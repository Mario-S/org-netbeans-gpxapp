package org.netbeans.gpx.model.xam;

import java.math.BigDecimal;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Waypoint extends AbstractGpxComponent implements WaypointType {

    private static final String LAT = "lat";

    private static final String LON = "lon";

    private Attribute latAttribute = new PrefixAttribute(LAT);

    private Attribute lonAttribute = new PrefixAttribute(LON);
    
    private static final SchemaType SCHEMA_TYPE = SchemaType.TRKPT;
    
    public Waypoint(GpxDocumentModelImpl model) {
        this(model, SCHEMA_TYPE);
    }

    public Waypoint(GpxDocumentModelImpl model, SchemaType schemaType) {
        super(model, schemaType);
    }
    
    public Waypoint(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return WaypointType.class;
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public double getLat() {
        return Double.parseDouble(getAttribute(latAttribute));
    }

    @Override
    public void setLat(double lat) {
        setAttribute(LAT, latAttribute, lat);
    }

    @Override
    public double getLon() {
        return Double.parseDouble(getAttribute(lonAttribute));
    }

    @Override
    public void setLon(double lon) {
        setAttribute(LON, lonAttribute, lon);
    }

    @Override
    public Elevation getEle() {
        return getChild(Elevation.class);
    }

    @Override
    public void setEle(Elevation ele) {
        removePreviousChild(getEle());

        addBefore(ele);
    }

    @Override
    public Time getTimeTag() {
        return getChild(Time.class);
    }

    @Override
    public void setTime(Time time) {
        removePreviousChild(getTimeTag());

        addBefore(time);
    }

    @Override
    public BigDecimal getElevation() {
        Elevation ele = getEle();
        return new BigDecimal(ele.getValue());
    }

    @Override
    public BigDecimal getLatitude() {
        return new BigDecimal(getLat());
    }

    @Override
    public BigDecimal getLongitude() {
        return new BigDecimal(getLon());
    }

    @Override
    public XMLGregorianCalendar getTime() {

        XMLGregorianCalendar cal = null;
        try {
            DatatypeFactory factory = DatatypeFactory.newInstance();
            cal = factory.newXMLGregorianCalendar(getTimeTag().getValue());
        } catch (DatatypeConfigurationException ex) {

        }
        return cal;
    }

    @Override
    public Magvar getMagvar() {
        return getChild(Magvar.class);
    }

    @Override
    public void setMagvar(Magvar magvar) {
        removePreviousChild(getMagvar());
        
        addBefore(magvar);
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
    public Symbol getSymbol() {
        return getChild(Symbol.class);
    }

    @Override
    public void setSymbol(Symbol sym) {
        removePreviousChild(getSymbol());
        
        addBefore(sym);
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
    public Satellite getSatellites() {
        return getChild(Satellite.class);
    }

    @Override
    public void setSatellites(Satellite sat) {
        removePreviousChild(getSatellites());
        
        addBefore(sat);
    }

    @Override
    public Hdop getHdop() {
        return getChild(Hdop.class);
    }

    @Override
    public void setHdop(Hdop hdop) {
        removePreviousChild(getHdop());
        
        addBefore(hdop);
    }

    @Override
    public Vdop getVdop() {
        return getChild(Vdop.class);
    }

    @Override
    public void setVdop(Vdop vdop) {
        removePreviousChild(getVdop());
        
        addBefore(vdop);
    }

    @Override
    public Pdop getPdop() {
        return getChild(Pdop.class);
    }

    @Override
    public void setPdop(Pdop pdop) {
        removePreviousChild(getPdop());
        
        addBefore(pdop);
    }

    @Override
    public AgeOfDGpsData getAgeOfDGpsData() {
        return getChild(AgeOfDGpsData.class);
    }

    @Override
    public void setAgeOfDGpsData(AgeOfDGpsData ageOfDGpsData) {
        removePreviousChild(getAgeOfDGpsData());
        
        addBefore(ageOfDGpsData);
    }

    @Override
    public DGpsId getDgpsId() {
        return getChild(DGpsId.class);
    }

    @Override
    public void setDGpsId(DGpsId dgpsId) {
        removePreviousChild(getDgpsId());
        
        addBefore(dgpsId);
    }

}
