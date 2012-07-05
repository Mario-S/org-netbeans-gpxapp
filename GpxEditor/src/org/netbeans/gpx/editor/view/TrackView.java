package org.netbeans.gpx.editor.view;

import com.topografix.gpx.model.GpxModel.SchemaType;
import com.topografix.gpx.model.Track;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.GpxNode;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionPanel;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author msc
 */
public class TrackView extends AbstractGpxView {
    
    public TrackView(InnerPanelFactory factory) {
        super(factory);
    }
    
    @Override
    public void addNodes(Children root, GpxDataObject gpxDataObject) {
        
        List<Track> tracks = getTracks(gpxDataObject);
        
        int size = tracks.size();
        Node[] trackNodes = new Node[size];
        SectionPanel[] panels = new SectionPanel[size];
        
        for (int i = 0; i < size; i++) {
            Track track = tracks.get(i);
            
            Node trackNode = new GpxNode(track.getName());
            trackNodes[i] = trackNode;
            
            panels[i] = new SectionPanel(this, trackNode, i);
        }
        
        root.add(trackNodes);
        
        for (int i = 0; i < size; i++) {

            if (i == 0) {
                addSection(panels[i], true);
            } else {
                addSection(panels[i]);
            }
            
        }
    }
    
    @Override
    protected Object getPanelKey(GpxDataObject gpxDataObject) {
        
        Track firstTrack = null;
        
        List<Track> tracks = getTracks(gpxDataObject);
        if (!tracks.isEmpty()) {
            firstTrack = tracks.get(0);
        }
        
        return firstTrack;
    }
    
    private List<Track> getTracks(GpxDataObject gpxDataObject) {
        return gpxDataObject.getGpx().getTracks();
    }
}
