/*
 * $$LastChangedRevision: 1 $$
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
abstract class AbstractInnerPanel extends SectionInnerPanel{
    
    protected GpxDataObject gpxDataObject;

    public AbstractInnerPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView);
        this.gpxDataObject = gpxDataObject;
    }
    
}
