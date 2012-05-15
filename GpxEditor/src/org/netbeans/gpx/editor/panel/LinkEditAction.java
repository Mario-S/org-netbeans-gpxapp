package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Link;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 * Action which displays the link edit dialog.
 * @author msc
 */
public class LinkEditAction extends AbstractAction {

    public static final String LINK_PROP = "link";
    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Link oldValue = new Link(link);
        LinkEditPanel panel = new LinkEditPanel(link);

        DialogDescriptor descriptor = new DialogDescriptor(panel, "Link");
        if (DialogDisplayer.getDefault().notify(descriptor)
                == NotifyDescriptor.OK_OPTION) {
            firePropertyChange(LINK_PROP, oldValue, link);
        }
    }
}
