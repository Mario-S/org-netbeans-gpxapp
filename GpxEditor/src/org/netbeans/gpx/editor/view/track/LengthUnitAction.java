package org.netbeans.gpx.editor.view.track;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import org.netbeans.gpx.unit.*;

/**
 *
 * @author msc
 */
class LengthUnitAction extends AbstractAction {
    
    final static String UNIT = "unit";

    private double total;

    private LengthUnitConverter unitConverter;

    private double converted;

    public LengthUnitAction() {
        unitConverter = new LengthUnitConverter();

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event != null) {
            JComboBox cb = (JComboBox) event.getSource();
            LengthUnit unit = (LengthUnit) cb.getSelectedItem();
            unitConverter.setTargetUnit(unit);
        }
        
        double old = converted;
        converted = unitConverter.convert(total);
        firePropertyChange(UNIT, old, converted);
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
