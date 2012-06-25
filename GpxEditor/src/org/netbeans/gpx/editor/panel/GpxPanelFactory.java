/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.GpxModel.SchemaType;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.overall.AuthorPanel;
import org.netbeans.gpx.editor.panel.overall.BoundsPanel;
import org.netbeans.gpx.editor.panel.overall.GpxBasicPanel;
import org.netbeans.gpx.editor.panel.overall.MetadataPanel;
import org.netbeans.gpx.editor.panel.track.TrackPanel;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class GpxPanelFactory implements InnerPanelFactory {
    
    private ToolBarDesignEditor designEditor;
    
    private GpxDataObject gpxDataObject;

    public GpxPanelFactory(ToolBarDesignEditor designEditor, GpxDataObject gpxDataObject) {
        this.designEditor = designEditor;
        this.gpxDataObject = gpxDataObject;
    }
    
    private SectionView getSectionView() {
        return (SectionView) designEditor.getContentView();
    }

    @Override
    public SectionInnerPanel createInnerPanel(Object key) {

        SectionInnerPanel panel = null;
        if (key instanceof SchemaType) {
            SchemaType type = (SchemaType) key;
            SectionView sectionView = getSectionView();

            switch (type) {
                case GPX:
                    panel = new GpxBasicPanel(sectionView, gpxDataObject);
                    break;
                case METADATA:
                    panel = new MetadataPanel(sectionView, gpxDataObject);
                    break;
                case PERSON:
                    panel = new AuthorPanel(sectionView, gpxDataObject);
                    break;
                case BOUNDS:
                    panel = new BoundsPanel(sectionView, gpxDataObject);
                    break;
                case TRACK:
                    panel = new TrackPanel(sectionView, gpxDataObject);
                    break;
                default:
                    panel = null;
            }
        }

        return panel;
    }
}
