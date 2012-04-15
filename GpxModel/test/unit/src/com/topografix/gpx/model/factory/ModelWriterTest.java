/*
 * $$LastChangedRevision: 1 $$
 * Created on 12.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package com.topografix.gpx.model.factory;

import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.Track;
import java.io.StringWriter;
import java.io.Writer;
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
    

    @Before
    public void setUp() throws JAXBException {
        
        String creator = "TEST";
        String trackName = "a track";

        Gpx model = new Gpx();
        model.setCreator(creator);
        Metadata metadata = new Metadata();
        model.setMetadata(metadata);

        Track track = new Track();
        track.setName(trackName);
        model.getTracks().add(track);

        classUnderTest = new ModelWriter(model);
    }

    @Test
    public void testWithWriter() throws JAXBException {
        Writer writer = new StringWriter();
        classUnderTest.write(writer);
        String str = writer.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains("\n"));
    }

}
