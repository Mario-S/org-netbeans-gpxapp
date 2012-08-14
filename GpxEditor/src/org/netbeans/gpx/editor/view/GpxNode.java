package org.netbeans.gpx.editor.view;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author msc
 */
public class GpxNode extends AbstractNode{

    public GpxNode(String name) {
        this(name, Children.LEAF);
    }

    public GpxNode(String name, Children children) {
        super(children);
        setDisplayName(name);
    }
    
    
}
