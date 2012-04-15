package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Gpx;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class PanelFactoryTest {
    
    private OverallPanelFactory classUnderTest;
    
    @Before
    public void setUp() {
        GpxDataObject dataObject = createNiceMock(GpxDataObject.class);
        AbstractNode root = new AbstractNode(Children.LEAF);
        SectionView sectionView = createNiceMock(SectionView.class);
        expect(sectionView.getRoot()).andReturn(root).atLeastOnce();
        replay(sectionView);
        
        ToolBarDesignEditor designEditor = new ToolBarDesignEditor();
        designEditor.setContentView(sectionView);
        classUnderTest = new OverallPanelFactory(dataObject, designEditor);
        
    }

    /**
     * Test with an unknonw key.
     */
    @Test
    public void testWithUnknownKey() {
      
      SectionInnerPanel panel = classUnderTest.createInnerPanel(new Object());  
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
        expect(gpx.getCreator()).andReturn("Test").atLeastOnce();
        replay(gpx);
        
        SectionInnerPanel panel = classUnderTest.createInnerPanel(gpx);
        assertNotNull(panel);
        assertTrue("panel is not of expected type", panel instanceof GpxBasicPanel);
        
        verify(gpx);
    }
}
