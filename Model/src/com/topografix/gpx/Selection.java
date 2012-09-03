/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package com.topografix.gpx;

import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
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
