/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.unit;

/**
 *
 * @author msc
 */
public enum LengthUnit {

    m(1), km(1000);

    private long factor;

    private LengthUnit(long factor) {
        this.factor = factor;
    }

    public long getFactor() {
        return factor;
    }
}
