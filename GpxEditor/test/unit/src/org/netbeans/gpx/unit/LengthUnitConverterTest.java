/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class LengthUnitConverterTest {
    
    private LengthUnitConverter classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new LengthUnitConverter();
    }

    /**
     * Test of convert method, of class LengthUnitConverter.
     */
    @Test
    public void testConvertWithSameUnits() {
        double total = 1.0;
        double expResult = 1.0;
        double result = classUnderTest.convert(total);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of convert method, of class LengthUnitConverter.
     */
    @Test
    public void testConvertMToKm() {
        double total = 1000.0;
        double expResult = 1.0;
        classUnderTest.setTargetUnit(LengthUnit.km);
        double result = classUnderTest.convert(total);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of convert method, of class LengthUnitConverter.
     */
    @Test
    public void testConvertKmToM() {
        double total = 1.0;
        double expResult = 1000.0;
        classUnderTest.setBaseUnit(LengthUnit.km);
        classUnderTest.setTargetUnit(LengthUnit.m);
        double result = classUnderTest.convert(total);
        assertEquals(expResult, result, 0.0);
    }
}
