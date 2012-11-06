package org.netbeans.gpx.unit;

import java.text.NumberFormat;
import java.util.List;
import org.netbeans.gpx.model.api.PositionCalculateable;
import org.netbeans.gpx.model.entity.Waypoint;
import org.openide.util.Lookup;

/**
 *
 * @author msc
 */
public class LengthUnitConverter {

    private LengthUnit baseUnit;

    private LengthUnit targetUnit;

    public LengthUnitConverter() {

        baseUnit = LengthUnit.m;
        targetUnit = LengthUnit.m;
    }

    public double convert(double total) {
        
        double converted = total;
        
        if(baseUnit != targetUnit){
            if(targetUnit.ordinal() > baseUnit.ordinal()){
                converted = converted / targetUnit.getFactor();
            }else{
                converted = converted * baseUnit.getFactor();
            }
        }

        return converted;
    }

    public LengthUnit getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(LengthUnit baseUnit) {
        this.baseUnit = baseUnit;
    }

    public LengthUnit getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(LengthUnit targetUnit) {
        this.targetUnit = targetUnit;
    }
}
