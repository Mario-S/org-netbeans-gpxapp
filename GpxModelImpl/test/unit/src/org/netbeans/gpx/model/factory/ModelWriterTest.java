/*
 * $$LastChangedRevision: 1 $$
 * Created on 12.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.model.factory;

import org.netbeans.gpx.model.factory.ModelWriter;
import org.netbeans.gpx.model.entity.Gpx;
import org.netbeans.gpx.model.entity.Metadata;
import org.netbeans.gpx.model.entity.Track;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class ModelWriterTest {

    private ModelWriter classUnderTest;

    private final Logger logger;

    private Gpx model;

    public ModelWriterTest() {
        logger = Logger.getLogger(getClass().getName());
    }

    @Before
    public void setUp() throws JAXBException {

        String creator = "TEST";
        String trackName = "a track";
        model = new Gpx();
        model.setCreator(creator);
        Metadata metadata = new Metadata();
        model.setMetadata(metadata);

        Track track = new Track();
        track.setName(trackName);
        model.getTracks().add(track);

        classUnderTest = new ModelWriter();
    }

    @Test
    public void testWrite() throws JAXBException {
        OutputStream stream = new ByteArrayOutputStream();
        classUnderTest.write(model, stream);
        String str = stream.toString();

        logger.log(Level.INFO, str);

        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains("\n"));
        assertTrue(str.contains("http://www.topografix.com/GPX/1/1/gpx.xsd"));
    }
}
