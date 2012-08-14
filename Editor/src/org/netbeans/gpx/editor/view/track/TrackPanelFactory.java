package org.netbeans.gpx.editor.view.track;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.AbstractInnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class TrackPanelFactory extends AbstractInnerPanelFactory{

    public TrackPanelFactory(ToolBarDesignEditor designEditor, GpxDataObject gpxDataObject) {
        super(designEditor, gpxDataObject);
    }

    @Override
    public SectionInnerPanel createInnerPanel(Object key) {
        int trackNumber = (Integer)key;
        return new TrackPanel(getSectionView(), gpxDataObject, trackNumber);
    }
    
}
