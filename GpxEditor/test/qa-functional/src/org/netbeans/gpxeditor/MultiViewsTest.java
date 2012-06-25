package org.netbeans.gpxeditor;

import java.io.File;
import junit.framework.Test;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.AbstractButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.junit.AssertionFailedErrorException;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author msc
 */
public class MultiViewsTest extends JellyTestCase {

    private static final String TEST_FILE = "meta.gpx";

    private GpxDataObject dataObject;

    public MultiViewsTest(String testName) {
        super(testName);
    }

    /** 
     * Creates suite from particular test cases. 
     */
    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(MultiViewsTest.class);
        testConfig = testConfig.addTest("testOverallView", "testTrackView");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    public void testOverallView() throws DataObjectNotFoundException {
        initDataObject();
        open(dataObject);
        TopComponentOperator operator = new TopComponentOperator(TEST_FILE);
        AbstractButtonOperator btnOperator = new AbstractButtonOperator(operator, 0);
        
        assertTrue(btnOperator.isEnabled());
        assertEquals("General", btnOperator.getText());
        btnOperator.clickMouse();
        
        JComboBoxOperator cmbOperator = new JComboBoxOperator(operator);
        assertTrue(cmbOperator.isEnabled());
        for (int i = 0; i < 5; i++) {
            cmbOperator.selectItem(i);
        }
    }
    
    public void testTrackView() throws DataObjectNotFoundException {
        initDataObject();
        open(dataObject);
        TopComponentOperator operator = new TopComponentOperator(TEST_FILE);
        AbstractButtonOperator btnOperator = new AbstractButtonOperator(operator, 1);
        
        assertTrue(btnOperator.isEnabled());
        assertEquals("Tracks", btnOperator.getText());
        btnOperator.clickMouse();
        
        JComboBoxOperator cmbOperator = new JComboBoxOperator(operator);
        assertTrue(cmbOperator.isEnabled());
        
    }

    private void initDataObject() throws DataObjectNotFoundException {
        if (dataObject == null) {
            File f = new File(getDataDir().getAbsolutePath() + "/" + TEST_FILE);
            FileObject fo = FileUtil.toFileObject(f);
            dataObject = ( (GpxDataObject) DataObject.find(fo) );
        }
    }

    private static void open(GpxDataObject dObj) {
        try {
            dObj.showElement(dObj.getGpx());
        } catch (Exception ex) {
            throw new AssertionFailedErrorException("Failed to switch to Design View", ex);
        }
    }

}
