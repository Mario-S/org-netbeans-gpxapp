package org.netbeans.gpx.editor.panel;

import org.netbeans.gpx.editor.view.overall.OverallPanelFactory;
import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.GpxModel.SchemaType;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.view.overall.GpxBasicPanel;
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
public class GpxPanelFactoryTest {
    
    private GpxDataObject dataObject;
    
    private OverallPanelFactory classUnderTest;
    
    @Before
    public void setUp() {
        dataObject = createNiceMock(GpxDataObject.class);
        AbstractNode root = new AbstractNode(Children.LEAF);
        SectionView sectionView = createNiceMock(SectionView.class);
        expect(sectionView.getRoot()).andReturn(root).atLeastOnce();
        replay(sectionView);
        
        ToolBarDesignEditor designEditor = new ToolBarDesignEditor();
        designEditor.setContentView(sectionView);
        classUnderTest = new OverallPanelFactory(designEditor, dataObject);
        
    }

    /**
     * Test with an unknonw key.
     */
    @Test
    public void testWithUnknownKey() {
      
      SectionInnerPanel panel = classUnderTest.createInnerPanel(SchemaType.TRACK);  
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
