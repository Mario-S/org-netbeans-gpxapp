package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Link;
import javax.swing.JPanel;

/**
 *
 * @author msc
 */
public class LinkPanel extends JPanel {
    protected Link link;
    
    public Link getModel() {
        return link;
    }

    public void setModel(Link model) {
        this.link = model;
    }
    
}
