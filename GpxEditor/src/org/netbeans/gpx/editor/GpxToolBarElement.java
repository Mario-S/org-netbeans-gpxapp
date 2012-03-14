/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor;

import org.netbeans.gpx.editor.view.GpxView;
import org.netbeans.gpx.editor.panel.PanelFactory;
import com.topografix.gpx.model.Gpx;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.xml.multiview.ToolBarMultiViewElement;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class GpxToolBarElement extends ToolBarMultiViewElement{
    
    private GpxDataObject gpxDataObject;
    private ToolBarDesignEditor designEditor;
    private SectionView view;
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
        view = createView();
        designEditor.setContentView(view);
        try {
            view.openPanel(gpxDataObject.getGpx());
        } catch (IOException ex) {
            Logger.getLogger(Gpx.class.getName()).log(Level.SEVERE, null, ex);;
        }
        view.checkValidity();
    }

    private SectionView createView() {
        return new GpxView(factory, gpxDataObject);
    }
    
    
    
}
