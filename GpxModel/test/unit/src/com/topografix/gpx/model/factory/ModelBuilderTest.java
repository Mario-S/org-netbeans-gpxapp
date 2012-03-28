/*
 * $$LastChangedRevision: 1 $$
 * Created on 08.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package com.topografix.gpx.model.factory;

import com.topografix.gpx.model.Link;
import com.topografix.gpx.model.Track;
import java.util.List;
import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.TrackSegment;
import com.topografix.gpx.model.Waypoint;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class ModelBuilderTest {

    private Gpx build(String resource) throws FileNotFoundException, JAXBException {
        String fileName = getClass().getResource(resource).getFile();
        File file = new File(fileName);
        ModelBuilder builder = new ModelBuilder(new FileInputStream(file));
        return builder.build();
    }

    @Test
    public void testTrack() throws FileNotFoundException, JAXBException {
        String resource = "track.gpx";
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
