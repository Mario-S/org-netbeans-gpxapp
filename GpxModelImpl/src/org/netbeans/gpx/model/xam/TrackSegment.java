package org.netbeans.gpx.model.xam;

import java.util.List;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class TrackSegment extends AbstractGpxComponent implements TrackSegmentType {

    private static final SchemaType SCHEMA_TYPE = SchemaType.TRKSEG;

    public TrackSegment(GpxDocumentModelImpl model) {
        super(model, SCHEMA_TYPE);
    }
    
    public TrackSegment(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    @Override
    public SchemaType getSchemaType() {
        return SCHEMA_TYPE;
    }

    @Override
    public Class<? extends GpxDocumentComponent> getComponentyType() {
        return TrackSegmentType.class;
    }

    @Override
    public void accept(GpxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public List<WaypointType> getTrackPoints() {
        return getChildren(WaypointType.class);
    }

    @Override
    public void setTrackPoints(List<WaypointType> trkpts) {

        List<WaypointType> previous = getTrackPoints();
        for (WaypointType wpt : previous) {
            removeChild(SchemaType.WPT, wpt);
        }

        for (WaypointType wpt : trkpts) {
            addBefore(wpt);
        }
    }

    @Override
    public void addTrackPoint(int index, WaypointType waypoint) {
        getTrackPoints().add(index, waypoint);
    }

    @Override
    public void removeTrackPoint(WaypointType waypoint) {
        removeChild(SCHEMA_TYPE.TRKPT, waypoint);
    }
}
