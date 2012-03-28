/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor;

import org.netbeans.gpx.editor.view.GeneralView;
import org.netbeans.gpx.editor.panel.PanelFactory;
import org.netbeans.gpx.editor.view.AbstractGpxView;
import org.netbeans.modules.xml.multiview.ToolBarMultiViewElement;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class GpxToolBarElement extends ToolBarMultiViewElement {

    private GpxDataObject gpxDataObject;
    private ToolBarDesignEditor designEditor;
    private AbstractGpxView view;
    private PanelFactory factory;

    public GpxToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        this.gpxDataObject = gpxDataObject;
        designEditor = new ToolBarDesignEditor();
        factory = new PanelFactory(gpxDataObject, designEditor);
        setVisualEditor(designEditor);
    }

    @Override
    public SectionView getSectionView() {
        return view;
    }

    @Override
    public void componentShowing() {
        super.componentShowing();
        
        gpxDataObject.updateModel();
        
        view = new GeneralView(factory);
        
        designEditor.setContentView(view);
        
        view.open(gpxDataObject);
    }

}
