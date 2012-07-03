/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.gpx.editor.panel;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.InnerPanelFactory;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 *
 * @author msc
 */
public abstract class AbstractInnerPanelFactory implements InnerPanelFactory {

    protected ToolBarDesignEditor designEditor;
    protected GpxDataObject gpxDataObject;

    public AbstractInnerPanelFactory(ToolBarDesignEditor designEditor, GpxDataObject gpxDataObject) {
        this.designEditor = designEditor;
        this.gpxDataObject = gpxDataObject;
    }

    protected SectionView getSectionView() {
        return (SectionView) designEditor.getContentView();
    }
}
