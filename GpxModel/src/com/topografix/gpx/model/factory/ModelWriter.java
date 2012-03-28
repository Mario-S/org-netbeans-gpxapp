/*
 * $$LastChangedRevision: 1 $$
 * Created on 12.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package com.topografix.gpx.model.factory;

import com.topografix.gpx.model.Gpx;
import java.io.OutputStream;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author msc
 */
public class ModelWriter {

    private Gpx model;

    private Marshaller marshaller;

    public ModelWriter(Gpx model) throws JAXBException {
        this.model = model;
        marshaller = createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    private Marshaller createMarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Gpx.class);
        return context.createMarshaller();
    }

    public void write(Writer writer) throws JAXBException {

        marshaller.marshal(model, writer);
    }

    public void write(OutputStream stream) throws JAXBException {
        marshaller.marshal(model, stream);
    }

}
