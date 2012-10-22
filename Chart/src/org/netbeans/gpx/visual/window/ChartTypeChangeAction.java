/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.visual.window;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import org.netbeans.gpx.visual.chart.ChartType;

/**
 *
 * @author msc
 */
class ChartTypeChangeAction extends AbstractAction {   
    
    private ChartType previous;

    public ChartTypeChangeAction() {
	previous = ChartType.DISTANCE;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	
	JComboBox cmb = (JComboBox) e.getSource();
	ChartType chartType = (ChartType) cmb.getSelectedItem();
	
	changeSupport.firePropertyChange(chartType.getClass().getSimpleName(), previous, chartType);
	previous = chartType;
    }
    
}
