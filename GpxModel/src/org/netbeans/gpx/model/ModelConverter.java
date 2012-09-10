package org.netbeans.gpx.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.netbeans.gpx.model.entity.Gpx;
import org.netbeans.gpx.model.factory.ModelReader;
import org.netbeans.gpx.model.factory.ModelWriter;

/**
 * A facade for serialization and deserialization of the gpx model.
 *
 * @author msc
 */
public final class ModelConverter {

    private ModelConverter() {
    }

    /**
     * Builds the model by reading from the input stream.
     * @param in Inputsream
     * @return Returns the model.
     * @throws IOException 
     */
    public static Gpx readModel(InputStream in) throws IOException {

        Gpx model = null;
        try {
            ModelReader builder = new ModelReader(in);
            model = builder.build();
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }

        return model;
    }

    /**
     * Writes the model to the output stream.
     * @param model the model
     * @param out the stream to write to.
     * @throws IOException 
     */
    public static void writeModel(Gpx model, OutputStream out) throws IOException {
        try {
            ModelWriter writer = new ModelWriter();
            writer.write(model, out);
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }
    }
}
