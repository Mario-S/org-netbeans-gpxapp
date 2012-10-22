package org.netbeans.gpx.model.api;



import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * Singleton for the lookup.
 * 
 * @author msc
 */
public enum Selection {
    
    INSTANCE;

    private Lookup lookup;

    private InstanceContent content;

    private Selection() {

        content = new InstanceContent();
        lookup = new AbstractLookup(content);
    }

    public InstanceContent getContent() {
        return content;
    }

    public Lookup getLookup() {
        return lookup;
    }
}
