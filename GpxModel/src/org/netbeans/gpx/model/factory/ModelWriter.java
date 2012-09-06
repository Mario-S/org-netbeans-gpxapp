/*
 * $$LastChangedRevision: 1 $$
 * Created on 12.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.model.factory;

import org.netbeans.gpx.model.entity.Gpx;
import org.netbeans.gpx.model.entity.GpxModel;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author msc
 */
public class ModelWriter {

    private Marshaller marshaller;

    private static String schemaLocation = GpxModel.Schema.NAME_SPACE + " " + GpxModel.Schema.LOCATION;

    public ModelWriter() throws JAXBException {
        marshaller = createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
    }

    private Marshaller createMarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Gpx.class);
        return context.createMarshaller();
    }

    public void write(Gpx model, OutputStream stream) throws JAXBException {

        marshaller.marshal(model, stream);
    }
}
