package org.netbeans.gpx.editor.panel.sub;

import javax.swing.JPanel;

/**
 *
 * @author msc
 */
abstract class AbstractSubPanel<T> extends JPanel {


    public abstract T getModel(); 
    
    public abstract void setModel(T model);
}
