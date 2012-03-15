/*
 * $$LastChangedRevision: 1 $$
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author msc
 */
public abstract class AbstractGpxView extends SectionView {

    private Children root;

    public AbstractGpxView(InnerPanelFactory factory) {
        super(factory);
        root = new Children.Array();
        setRoot(new AbstractNode(root));
    }

    protected class GpxSchemaNode extends AbstractNode {

        public GpxSchemaNode(String name) {
            super(Children.LEAF);
            setDisplayName(name);
        }

    }
    
    public void open(GpxDataObject gpxDataObject){
        
        addNodes(root, gpxDataObject);
        
        openPanel(getPanelKey(gpxDataObject));
        checkValidity();
    }
    
    public abstract void addNodes(Children root, GpxDataObject gpxDataObject);

    protected abstract Object getPanelKey(GpxDataObject gpxDataObject);
}
