package org.netbeans.gpx.model.xam;

import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class Bounds extends AbstractGpxComponent implements BoundsType {
    
    private static final String MIN_LAT = "minlat";
    
    private static final String MIN_LON = "minlon";
    
    private static final String MAX_LAT = "maxlat";
    
    private static final String MAX_LON = "maxlon";
    
    private Attribute minLatAttribute = new PrefixAttribute(MIN_LAT);
    
    private Attribute minLonAttribute = new PrefixAttribute(MIN_LON);
    
    private Attribute maxLatAttribute = new PrefixAttribute(MAX_LAT);
    
    private Attribute maxLonAttribute = new PrefixAttribute(MAX_LON);
    
    private static final SchemaType SCHEMA_TYPE =  SchemaType.BOUNDS;

    public Bounds(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }
    
    public Bounds(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }
    
    private double toDouble(String value){
        return Double.parseDouble(value);
    }
    
    private double getBound(Attribute attr){
        return toDouble(getAttribute(attr));
    }
    
    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }
    
    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return BoundsType.class;
    }
    
    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public double getMinLat() {
        return getBound(minLatAttribute);
    }
    
    @Override
    public void setMinLat(double minLat) {
        setAttribute(MIN_LAT, minLatAttribute, minLat);
    }
    
    @Override
    public double getMinLon() {
        return getBound(minLonAttribute);
    }
    
    @Override
    public void setMinLon(double minLon) {
        setAttribute(MIN_LON, minLonAttribute, minLon);
    }
    
    @Override
    public double getMaxLat() {
        return getBound(maxLatAttribute);
    }
    
    @Override
    public void setMaxLat(double maxLat) {
        setAttribute(MAX_LAT, maxLatAttribute, maxLat);
    }
    
    @Override
    public double getMaxLon() {
        return getBound(maxLonAttribute);
    }
    
    @Override
    public void setMaxLon(double maxLon) {
        setAttribute(MAX_LON, maxLonAttribute, maxLon);
    }
}
