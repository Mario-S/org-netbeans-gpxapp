package org.netbeans.gpx.model.xam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.netbeans.gpx.model.entity.GpxModel;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentComponent;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentModel;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author msc
 */
public abstract class AbstractGpxComponent extends AbstractDocumentComponent<GpxDocumentComponent> implements
    GpxDocumentComponent {

    protected final static int FIRST = 0;

    public AbstractGpxComponent(GpxDocumentModelImpl model, SchemaType schemaType) {
        this(model, createNewElement(model, schemaType.toString()));
    }

    public AbstractGpxComponent(GpxDocumentModelImpl model, Element e) {
        super(model, e);
    }

    private static Element createNewElement(GpxDocumentModel model, String name) {
        return model.getDocument().createElementNS(GpxModel.Schema.NAME_SPACE.toString(), name);
    }

    @Override
    protected void populateChildren(List<GpxDocumentComponent> children) {
        NodeList nl = getPeer().getChildNodes();
        if (nl != null) {
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n instanceof Element) {
                    GpxDocumentComponent comp = (GpxDocumentComponent) getModel().createComponent(this, (Element) n);
                    if (comp != null) {
                        children.add(comp);
                    }
                }
            }
        }
    }

    @Override
    protected Object getAttributeValueOf(Attribute atrbt, String string) {
        return null;
    }

    @Override
    public String getTagName() {
        return getSchemaType().toString();
    }

    protected Collection addBefore(GpxDocumentComponent type) {

        Collection typeList = new ArrayList();
        if (type != null) {
            addBefore(type.getSchemaType(), type, typeList);
        }
        return typeList;
    }

    protected synchronized void addBefore(GpxModel.SchemaType type, GpxDocumentComponent component,
        Collection<Class<? extends GpxDocumentComponent>> typeList) {
        super.addBefore(type.toString().toLowerCase(), component, typeList);
    }

    public synchronized void removeChild(GpxModel.SchemaType type, GpxDocumentComponent component) {
        super.removeChild(type.toString().toLowerCase(), component);
    }

    protected void removePreviousChild(GpxDocumentComponent prev) {
        if (prev != null) {
            removeChild(prev.getSchemaType(), prev);
        }
    }
}
