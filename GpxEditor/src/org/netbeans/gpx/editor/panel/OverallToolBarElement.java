/*
 * $$LastChangedRevision: 1 $$
 * Created on 15.02.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.view.OverallView;
import org.netbeans.gpx.editor.panel.OverallPanelFactory;
import org.netbeans.gpx.editor.view.AbstractGpxView;
import org.netbeans.modules.xml.multiview.ToolBarMultiViewElement;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public class OverallToolBarElement extends ToolBarMultiViewElement {

    private GpxDataObject gpxDataObject;
    private ToolBarDesignEditor toolbarEditor;
    private AbstractGpxView view;
    private OverallPanelFactory factory;

    public OverallToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        this.gpxDataObject = gpxDataObject;
        toolbarEditor = new ToolBarDesignEditor();
        factory = new OverallPanelFactory(gpxDataObject, toolbarEditor);
        setVisualEditor(toolbarEditor);
    }

    @Override
    public SectionView getSectionView() {
        return view;
    }

    @Override
    public void componentShowing() {
        super.componentShowing();

        gpxDataObject.updateModel();

        view = new OverallView(factory);
        toolbarEditor.setContentView(view);

        view.open(gpxDataObject, toolbarEditor.getLastActive());
    }
}
