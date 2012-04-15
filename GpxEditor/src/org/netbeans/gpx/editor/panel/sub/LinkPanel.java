package org.netbeans.gpx.editor.panel.sub;

import com.topografix.gpx.model.Link;

/**
 *
 * @author msc
 */
public class LinkPanel extends AbstractSubPanel<Link> {
    protected Link link;
    
    @Override
    public Link getModel() {
        return link;
    }

    @Override
    public void setModel(Link model) {
        this.link = model;
    }
    
}
