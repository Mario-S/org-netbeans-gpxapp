/*
 * $$LastChangedRevision: 1 $$
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Gpx;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
public abstract class AbstractInnerPanel extends SectionInnerPanel{
    
    private Gpx gpx;
    
    protected GpxDataObject gpxDataObject;

    public AbstractInnerPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView);
        this.gpxDataObject = gpxDataObject;
        this.gpx = gpxDataObject.getGpx();
    }
    
    protected final Gpx getGpx(){
        return gpx;
    }

    @Override
    protected void startUIChange() {
        gpxDataObject.setChangedFromUI(true);
    }

    @Override
    protected void endUIChange() {
        gpxDataObject.updateModel();
        gpxDataObject.setChangedFromUI(false);
    }
    
}
