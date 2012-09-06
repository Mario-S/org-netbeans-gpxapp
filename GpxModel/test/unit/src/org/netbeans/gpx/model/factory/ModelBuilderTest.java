/*
 * $$LastChangedRevision: 1 $$
 * Created on 08.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.model.factory;

import org.netbeans.gpx.model.factory.ModelBuilder;
import org.netbeans.gpx.model.entity.Link;
import org.netbeans.gpx.model.entity.Track;
import java.util.List;
import org.netbeans.gpx.model.entity.Gpx;
import org.netbeans.gpx.model.entity.Metadata;
import org.netbeans.gpx.model.entity.TrackSegment;
import org.netbeans.gpx.model.entity.Waypoint;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class ModelBuilderTest extends NbTestCase {

    public ModelBuilderTest(String name) {
        super(name);
    }

    private Gpx build(String resource) throws FileNotFoundException, JAXBException {
        
        String fileName = getDataDir().getAbsolutePath() + resource;
        File file = new File(fileName);
        ModelBuilder builder = new ModelBuilder(new FileInputStream(file));
        return builder.build();
    }

    public void testTrack() throws FileNotFoundException, JAXBException {
        String resource = "/track.gpx";
        Gpx gpx = build(resource);

        Metadata metadata = gpx.getMetadata();
        assertNotNull(metadata);
        List<Link> links = metadata.getLinks();
        assertNotNull(links);
        assertFalse(links.isEmpty());

        assertNotNull(gpx);

        List<Track> tracks = gpx.getTracks();
        assertNotNull(tracks);
        assertFalse(tracks.isEmpty());
        assertEquals(1, tracks.size());

        Track track = tracks.get(0);
        List<TrackSegment> segments = track.getTrackSegments();
        assertNotNull(segments);
        assertFalse(segments.isEmpty());
        assertEquals(1, segments.size());

        TrackSegment segement = segments.get(0);
        List<Waypoint> points = segement.getTrackpoints();
        assertNotNull(points);
        assertFalse(points.isEmpty());
        assertTrue(points.size() > 1);
    }

}
