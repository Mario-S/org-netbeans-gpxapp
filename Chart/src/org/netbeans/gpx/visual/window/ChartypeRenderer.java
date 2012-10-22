package org.netbeans.gpx.visual.window;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.netbeans.gpx.visual.chart.ChartType;

/**
 *
 * @author msc
 */
class ChartTypeRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	    boolean cellHasFocus) {
	
	ChartType chartType = (ChartType) value;
	
	String translation; //TODO i18n
	if(chartType == ChartType.DISTANCE){
	    translation = "Distance (m)";
	}else{
	    translation = "Time (h:m:s)";
	}
	return super.getListCellRendererComponent(list, translation, index, isSelected, cellHasFocus);
    }
    
}
