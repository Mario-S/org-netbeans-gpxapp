/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.gpx.editor.panel.overall;

import com.topografix.gpx.model.Metadata;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.AbstractInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 * Sub class for panel related to Metadata
 * @author msc
 */
abstract class AbstractMetadataPanel extends AbstractInnerPanel {
    
    private static Metadata metadata;

    public AbstractMetadataPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView, gpxDataObject);
        
        metadata = getGpx().getMetadata();
    }
    
    protected Metadata getMetadata(){
        return metadata;
    }

    protected void setMetadata(Metadata other) {
        metadata = other;
    }
    
    /**
     * merges the object into Gpx
     */
    protected void merge(){
        getGpx().setMetadata(metadata);
    }

    
    /**
     * Creates the object if necessary.
     * @return 
     */
    protected Metadata checkMetadata() {
        if (metadata == null) {
            metadata = new Metadata();
        }
        return metadata;
    }

    @Override
    protected void startUIChange() {
        merge();
        super.startUIChange();
    }
    
}
