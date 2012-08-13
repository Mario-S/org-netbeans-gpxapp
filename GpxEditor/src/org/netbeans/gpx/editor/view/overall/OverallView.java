/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view.overall;

import com.topografix.gpx.model.GpxModel.SchemaType;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.GpxNode;
import org.netbeans.gpx.editor.view.AbstractGpxView;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionContainer;
import org.netbeans.modules.xml.multiview.ui.SectionContainerNode;
import org.netbeans.modules.xml.multiview.ui.SectionPanel;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 * Section view for meta data, creator and version.
 * @author msc
 */
public class OverallView extends AbstractGpxView {
    
    public OverallView(InnerPanelFactory factory) {
        super(factory);
    }
     
    @Override
    protected Object getPanelKey(GpxDataObject gpxDataObject) {
        return gpxDataObject.getGpx();
    }
    
    @Override
    public void addNodes(Children root, GpxDataObject gpxDataObject) {
        
        Node gpxNode = new GpxNode(gpxDataObject.getPrimaryFile().getName());
        
        Node [] metadataNodes = new Node[]{new GpxNode("Name"), 
            new GpxNode("Author"), new GpxNode("Bounds")};
        Children ch = new Children.Array();
        ch.add(metadataNodes);
        Node metadataNode = new SectionContainerNode(ch);
        metadataNode.setDisplayName("Metadata");
        root.add(new Node[] {gpxNode, metadataNode});
        
        //add panels
        SectionPanel gpxPanel = new SectionPanel(this, gpxNode, SchemaType.GPX);
        addSection(gpxPanel, true);
        
        SectionContainer metadataContainer = new SectionContainer(this,metadataNode,"Metadata");
        metadataContainer.addSection(new SectionPanel(this, metadataNodes[0], SchemaType.METADATA));
        metadataContainer.addSection(new SectionPanel(this, metadataNodes[1], SchemaType.PERSON));
        metadataContainer.addSection(new SectionPanel(this, metadataNodes[2], SchemaType.BOUNDS));
        addSection(metadataContainer);

    }
    
}
