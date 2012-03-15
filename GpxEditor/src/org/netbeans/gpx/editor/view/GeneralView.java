/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.Metadata;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionContainer;
import org.netbeans.modules.xml.multiview.ui.SectionContainerNode;
import org.netbeans.modules.xml.multiview.ui.SectionPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author msc
 */
public class GeneralView extends AbstractGpxView {
    
    public GeneralView(InnerPanelFactory factory) {
        super(factory);
    }
     
    @Override
    protected Object getPanelKey(GpxDataObject gpxDataObject) {
        return gpxDataObject.getGpx();
    }
    
    @Override
    public void addNodes(Children root, GpxDataObject gpxDataObject) {
        
        Gpx gpx = gpxDataObject.getGpx();
        Node gpxNode = new GpxSchemaNode(gpxDataObject.getPrimaryFile().getName());
        
        
        Metadata metadata = gpx.getMetadata();
        String name = metadata.getName();
        name = ( name == null || name.isEmpty() ) ? "Metadata" : name;
        Node metadataNode = new GpxSchemaNode(name);
        
        addSection(new SectionPanel(this, gpxNode, gpx));
        addSection(new SectionPanel(this, metadataNode, metadata));
        
        root.add(new Node[] {gpxNode, metadataNode});;
    }
    
}
