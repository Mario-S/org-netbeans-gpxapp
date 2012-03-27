/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Gpx;
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
public class PanelFactory implements InnerPanelFactory{
    
    private GpxDataObject gpxDataObject;
    private ToolBarDesignEditor designEditor;

    public PanelFactory(GpxDataObject gpxDataObject, ToolBarDesignEditor designEditor) {
        this.gpxDataObject = gpxDataObject;
        this.designEditor = designEditor;
    }

    @Override
    public SectionInnerPanel createInnerPanel(Object key) {
        
        SectionInnerPanel panel = null;
        if(key instanceof Gpx){
            panel = new GpxPanel(getSectionView(), gpxDataObject, (Gpx) key);
        }else if(key instanceof Metadata){
            panel = new MetadataPanel(getSectionView(), gpxDataObject, (Metadata) key);
        }
        
        return panel;
    }
    
    private SectionView getSectionView(){
        return (SectionView) designEditor.getContentView();
    }
    
    
}
