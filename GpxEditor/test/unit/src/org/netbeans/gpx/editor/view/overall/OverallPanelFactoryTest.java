package org.netbeans.gpx.editor.view.overall;

import org.netbeans.gpx.model.entity.Gpx;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class OverallPanelFactoryTest {
    
    private GpxDataObject dataObject;
    
    private OverallPanelFactory classUnderTest;
    
    @Before
    public void setUp() {
        dataObject = createNiceMock(GpxDataObject.class);
        
        ToolBarDesignEditor designEditor = new ToolBarDesignEditor();
        classUnderTest = new OverallPanelFactory(designEditor, dataObject);
        
    }

    /**
     * Test with an unknonw key.
     */
    @Test
    public void testWithUnknownKey() {
      
      SectionInnerPanel panel = classUnderTest.createInnerPanel(SchemaType.TRK);  
      assertNull(panel);
      
      panel = classUnderTest.createInnerPanel(null);  
      assertNull(panel);
    }

    /**
     * Test of create GpxPanel.
     */
    @Test
    public void testCreateGpxPanel() {
        
        Gpx gpx = createMock(Gpx.class);
        expect(gpx.getVersion()).andReturn("1.1").atLeastOnce();
        expect(gpx.getCreator()).andReturn("Test").once();
        replay(gpx);
        
        expect(dataObject.getGpx()).andReturn(gpx).once();
        replay(dataObject);
        
        SectionInnerPanel panel = classUnderTest.createInnerPanel(SchemaType.GPX);
        assertNotNull(panel);
        assertTrue("panel is not of expected type", panel instanceof GpxBasicPanel);
        
        verify(dataObject);
        verify(gpx);
    }
}
