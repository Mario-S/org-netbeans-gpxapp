package org.netbeans.visual;

import junit.framework.Test;
import org.netbeans.gpx.visual.chart.ChartType;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.junit.NbModuleSuite;


/**
 *
 * @author msc
 */
public class ElevationComponentTest extends JellyTestCase{
    private static final String WINDOW = "Elevation Window";
    private TopComponentOperator operator;

    public ElevationComponentTest(String testName) {
	super(testName);
    }
   
    public static Test suite() {
        NbModuleSuite.Configuration testConfig = NbModuleSuite.createConfiguration(ElevationComponentTest.class);
        testConfig = testConfig.addTest("testComboBox");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	
	String path = "Window|GPX";
	String elevationMenu = "Elevation";
	
	MainWindowOperator mainWindowOperator = MainWindowOperator.getDefault();
	mainWindowOperator.menuBar().pushMenu(path+"|"+elevationMenu, "|");
	
	operator = new TopComponentOperator(WINDOW);
    }
    
    
    
    public void testComboBox(){
	
	JComboBoxOperator cmbOperator = new JComboBoxOperator(operator);
        assertTrue(cmbOperator.isEnabled());
	
	String cmbText = cmbOperator.getSelectedItem().toString();
	assertEquals(ChartType.DISTANCE.toString(), cmbText);
	
	cmbOperator.selectItem(1);
	cmbText = cmbOperator.getSelectedItem().toString();
	assertEquals(ChartType.TIME.toString(), cmbText);
	
    }
}
