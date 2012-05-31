package org.netbeans.gpxeditor;

import java.awt.Component;
import java.io.File;
import javax.swing.JToolBar;
import junit.framework.Test;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.AbstractButtonOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator.JComboBoxFinder;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.junit.AssertionFailedErrorException;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;
import org.openide.cookies.EditCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author msc
 */
public class OverallPanelTest extends JellyTestCase{
    
    private static final String TEST_FILE = "meta.gpx";
    
    private GpxDataObject dataObject;

    public OverallPanelTest(String testName) {
        super(testName);
    }
    
    
    /** 
     * Creates suite from particular test cases. 
     */
    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(OverallPanelTest.class);
        testConfig = testConfig.addTest("testOverallPanel");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }
    
    public void testOverallPanel() throws DataObjectNotFoundException{
        initDataObject();
        open(dataObject);
        TopComponentOperator ed = new TopComponentOperator(TEST_FILE);
        AbstractButtonOperator btnOperator = new AbstractButtonOperator(ed, 0);
        assertTrue(btnOperator.isEnabled());
        assertEquals("General", btnOperator.getText());
        btnOperator.clickMouse();
        JComboBoxOperator cmbOperator = new JComboBoxOperator(ed);
        assertTrue(cmbOperator.isEnabled());
        cmbOperator.selectItem(1);
    }
    
    private void initDataObject() throws DataObjectNotFoundException {
        if (dataObject == null) {
            File f = new File(getDataDir().getAbsolutePath() + "/" + TEST_FILE);
            FileObject fo = FileUtil.toFileObject(f);
            dataObject = ((GpxDataObject) DataObject.find(fo));
        }
    }

    private static void open(GpxDataObject dObj) {
        try {
            dObj.showElement(dObj.getGpx());
        } catch (Exception ex) {
            throw new AssertionFailedErrorException("Failed to switch to Design View",ex);
        }
    }
}
