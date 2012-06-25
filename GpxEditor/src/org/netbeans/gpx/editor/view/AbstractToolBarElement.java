package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.panel.GpxPanelFactory;
import org.netbeans.modules.xml.multiview.ToolBarMultiViewElement;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 * Base class for all toolbar elements.
 * @author msc
 */
public abstract class AbstractToolBarElement extends ToolBarMultiViewElement {

    protected GpxDataObject gpxDataObject;
    protected ToolBarDesignEditor toolbarEditor;
    private InnerPanelFactory factory;
    private AbstractGpxView view;

    public AbstractToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        this.gpxDataObject = gpxDataObject;

        toolbarEditor = new ToolBarDesignEditor();
        setVisualEditor(toolbarEditor);
        
        factory = new GpxPanelFactory(toolbarEditor, gpxDataObject);
    }

    @Override
    public SectionView getSectionView() {
        return view;
    }

    @Override
    public void componentShowing() {
        super.componentShowing();

        gpxDataObject.updateModel();

        view = getView(factory);
        toolbarEditor.setContentView(view);

        view.open(gpxDataObject, toolbarEditor.getLastActive());
    }

    protected abstract AbstractGpxView getView(InnerPanelFactory factory);
}
