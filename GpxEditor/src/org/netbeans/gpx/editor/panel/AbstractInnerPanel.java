/*
 * $$LastChangedRevision: 1 $$
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import org.netbeans.gpx.model.entity.Gpx;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
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
    
    /**
     * Adds a {@link ListDataListener} to the {@link ListModel} which update the GPX model.
     * @param listModel the list model
     */
    protected void addListDataListener(ListModel listModel){
        listModel.addListDataListener(new ListModelModifyListener());
    }
    
    /**
     * Listener for any changes in the list model.
     * It also updates the GPX model.
     */
    private class ListModelModifyListener implements ListDataListener{

        @Override
        public void intervalAdded(ListDataEvent e) {
            updateModel();
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            updateModel();
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            updateModel();
        }
        
        private void updateModel(){
            startUIChange();
            endUIChange();
        }
        
    }
}
