/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.view.overall;

import com.topografix.gpx.model.GpxModel.SchemaType;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.AbstractInnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class OverallPanelFactory extends AbstractInnerPanelFactory {

    public OverallPanelFactory(ToolBarDesignEditor designEditor, GpxDataObject gpxDataObject) {
        super(designEditor, gpxDataObject);
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
                default:
                    panel = null;
            }
        }

        return panel;
    }
}
