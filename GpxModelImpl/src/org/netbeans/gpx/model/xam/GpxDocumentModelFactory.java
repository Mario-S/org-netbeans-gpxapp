package org.netbeans.gpx.model.xam;

import org.netbeans.modules.xml.xam.AbstractModelFactory;
import org.netbeans.modules.xml.xam.ModelSource;

/**
 *
 * @author msc
 */
public class GpxDocumentModelFactory extends AbstractModelFactory<GpxDocumentModel>{
    
    private static final GpxDocumentModelFactory INSTANCE = new GpxDocumentModelFactory();

    private GpxDocumentModelFactory() {
    }

    public static GpxDocumentModelFactory getInstance() {
        return INSTANCE;
    }

    @Override
    protected GpxDocumentModel createModel(ModelSource ms) {
        return new GpxDocumentModelImpl(ms);
    }
    
}
