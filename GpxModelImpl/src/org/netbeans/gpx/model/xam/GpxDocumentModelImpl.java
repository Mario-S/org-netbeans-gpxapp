package org.netbeans.gpx.model.xam;

import org.netbeans.modules.xml.xam.ComponentUpdater;
import org.netbeans.modules.xml.xam.ModelSource;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentModel;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class GpxDocumentModelImpl extends AbstractDocumentModel<GpxDocumentComponent> implements GpxDocumentModel {

    private GpxType root;

    private GpxDocumentComponentFactory factory;
    
    private SyncUpdateVisitor syncUpdateVisitor;

    public GpxDocumentModelImpl(ModelSource source) {
        super(source);
        factory = new GpxDocumentComponentFactoryImpl(this);
        syncUpdateVisitor = new SyncUpdateVisitor();
    }

    @Override
    public GpxDocumentComponent createRootComponent(Element elmnt) {
        GpxType created = (GpxType) getFactory().createComponent(elmnt, null);

        if (created != null) {
            root = created;
        }

        return created;
    }

    @Override
    protected ComponentUpdater<GpxDocumentComponent> getComponentUpdater() {
        return syncUpdateVisitor;
    }

    @Override
    public GpxDocumentComponent getRootComponent() {
        return root;
    }

    @Override
    public GpxDocumentComponent createComponent(GpxDocumentComponent parent, Element elmnt) {
        return getFactory().createComponent(elmnt, parent);
    }

    @Override
    public GpxType getGpx() {
        return (GpxType) getRootComponent();
    }

    @Override
    public GpxDocumentComponentFactory getFactory() {
        return factory;
    }
}
