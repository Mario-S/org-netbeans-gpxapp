package org.netbeans.gpx.explorer;

import java.awt.Component;
import java.awt.Point;
import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTreeOperator.JTreeFinder;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;

/**
 * functional test for the explorer window
 * @author msc
 */
public class ExplorerTest extends JellyTestCase{

    public ExplorerTest(String testName) {
        super(testName);
    }
    
    /** 
     * Creates suite from particular test cases. 
     */
    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ExplorerTest.class);
        testConfig = testConfig.addTest("testNewFile");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }
    
    public void testNewFile() throws Exception{
        
        TopComponentOperator fav = new TopComponentOperator("Favorites");
        ComponentChooser treeFinder = new JTreeFinder();
        Component tree = fav.findSubComponent(treeFinder);
        Point loc = tree.getLocation();
        fav.clickMouse(loc.x+25, loc.y+10, 1);
        
        JButtonOperator btn = new JButtonOperator(MainWindowOperator.getDefault());
        assertTrue(btn.isEnabled());
        btn.clickMouse();
        
        DialogOperator dlg = new DialogOperator("New File");
        assertTrue(dlg.isVisible());
    }
}
