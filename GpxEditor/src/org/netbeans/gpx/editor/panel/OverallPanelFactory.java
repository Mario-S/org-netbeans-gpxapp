/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.GpxModel.SchemaType;
import com.topografix.gpx.model.Metadata;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class OverallPanelFactory implements InnerPanelFactory {

    private GpxDataObject gpxDataObject;
    private ToolBarDesignEditor designEditor;
    private Metadata metadata;

    public OverallPanelFactory(GpxDataObject gpxDataObject, ToolBarDesignEditor designEditor) {
        this.gpxDataObject = gpxDataObject;
        this.designEditor = designEditor;
    }

    @Override
    public SectionInnerPanel createInnerPanel(Object key) {

        SectionInnerPanel panel;
        SchemaType type = (SchemaType) key;
        
        switch (type) {
            case GPX:
                panel = new GpxBasicPanel(getSectionView(), gpxDataObject);
                break;
            case METADATA:
                panel = new MetadataPanel(getSectionView(), gpxDataObject);
                break;
            case PERSON:
                panel = new AuthorPanel(getSectionView(), gpxDataObject);
                break;
            case BOUNDS:
                panel = new BoundsPanel(getSectionView(), gpxDataObject);
                break;
            default:
                panel = null;
        }

        return panel;
    }

    private SectionView getSectionView() {
        return (SectionView) designEditor.getContentView();
    }
}
