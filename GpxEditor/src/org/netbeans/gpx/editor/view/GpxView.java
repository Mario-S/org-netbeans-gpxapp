/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view;

import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.Metadata;
import java.io.IOException;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author msc
 */
public class GpxView extends SectionView {

    private GpxDataObject gpxDataObject;

    public GpxView(InnerPanelFactory factory, GpxDataObject gpxDataObject) {
        super(factory);
        this.gpxDataObject = gpxDataObject;
        init();
        
    }

    private void init() {
        Children rootChildren = new Children.Array();
        Node root = new AbstractNode(rootChildren);

//        try {

            Gpx gpx = gpxDataObject.getGpx();
            Node gpxNode = new GpxSchemaNode(gpxDataObject.getPrimaryFile().getName());
            addSection(new SectionPanel(this, gpxNode, gpx));
            
            Metadata metadata = gpx.getMetadata();
            Node metadataNode = new GpxSchemaNode(metadata.getName());
            addSection(new SectionPanel(this, metadataNode, metadata));

//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//        }

        setRoot(root);
        
        openPanel(gpx);
        openPanel(metadata);
    }

    private class GpxSchemaNode extends AbstractNode {

        public GpxSchemaNode(String name) {
            super(Children.LEAF);
            setDisplayName(name);
        }

    }

}
