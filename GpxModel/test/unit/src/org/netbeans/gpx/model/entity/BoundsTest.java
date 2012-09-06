package org.netbeans.gpx.model.entity;

import org.netbeans.gpx.model.entity.Bounds;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class BoundsTest {
   
    @Test
    public void testHasContent() {
        Bounds instance = new Bounds();
        assertFalse(instance.hasContent());
        instance.setMaxlat(BigDecimal.ZERO);
        assertTrue(instance.hasContent());
    }
}
