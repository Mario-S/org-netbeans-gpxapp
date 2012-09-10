/*
 * $$LastChangedRevision: 1 $$
 * Created on 13.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.model.factory;

import org.netbeans.gpx.model.entity.Gpx;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author msc
 */
public class ModelReader {

    private InputStream in;

    public ModelReader(InputStream in) {
        this.in = in;
    }

    public Gpx build() throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Gpx.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Gpx) unmarshaller.unmarshal(in);
    }

}
