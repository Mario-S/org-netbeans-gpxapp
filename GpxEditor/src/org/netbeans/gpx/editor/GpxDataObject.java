package org.netbeans.gpx.editor;

import org.netbeans.gpx.editor.view.GeneralViewDesc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBException;

import org.netbeans.modules.xml.multiview.DesignMultiViewDesc;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataObject;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataSynchronizer;
import org.netbeans.spi.xml.cookies.CheckXMLSupport;
import org.netbeans.spi.xml.cookies.DataObjectAdapters;
import org.netbeans.spi.xml.cookies.ValidateXMLSupport;
import org.openide.ErrorManager;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Children;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.xml.sax.InputSource;

import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.factory.ModelBuilder;
import com.topografix.gpx.model.factory.ModelWriter;
import java.util.logging.Logger;

public class GpxDataObject extends XmlMultiViewDataObject {

    private final ModelSynchronizer modelSynchronizer;

    private Gpx gpx;

    public GpxDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        modelSynchronizer = new ModelSynchronizer(this);

        CookieSet cookies = getCookieSet();
        InputSource is = DataObjectAdapters.inputSource(this);
        cookies.add((Node.Cookie) new CheckXMLSupport(is));
        cookies.add((Node.Cookie) new ValidateXMLSupport(is));
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.LEAF, getLookup());
    }

    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }

    @Override
    protected String getPrefixMark() {
        return null;
    }

    @Override
    protected DesignMultiViewDesc[] getMultiViewDesc() {
        return new DesignMultiViewDesc[] {new GeneralViewDesc(this)};
    }

    private void parseDocument() throws IOException {
        if (gpx == null) {
            gpx = getGpx();
        }
        else {
            gpx = loadFromEditor();
        }
    }

    public Gpx getGpx() throws IOException {

        if (gpx == null) {
            gpx = loadFromFile();
        }
        return gpx;
    }

    private Gpx loadFromFile() throws IOException {

        File file = FileUtil.toFile(getPrimaryFile());
        return build(new FileInputStream(file));
    }

    private Gpx loadFromEditor() throws IOException {
        InputStream stream = getEditorSupport().getInputStream();
        return build(stream);
    }

    private Gpx build(InputStream stream) throws IOException {

        Gpx model = null;
        ModelBuilder builder = new ModelBuilder(stream);
        try {
            model = builder.build();
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }
        return model;
    }

    public void updateModel() {
        modelSynchronizer.requestUpdateData();
    }

    /**
     * class to synchronize model with editor content
     */
    private class ModelSynchronizer extends XmlMultiViewDataSynchronizer {

        public static final int DELAY = 500;

        public ModelSynchronizer(GpxDataObject dataObject) {
            this(dataObject, DELAY);
        }

        public ModelSynchronizer(GpxDataObject dataObject, int delay) {
            super(dataObject, delay);
        }

        @Override
        protected boolean mayUpdateData(boolean bln) {
            return true;
        }

        @Override
        protected void updateDataFromModel(Object model, FileLock lock, boolean modify) {
            if (model != null) {
                try {
                    Writer out = new StringWriter();
                    ModelWriter writer = new ModelWriter((Gpx) model);
                    writer.write(out);
                    getDataCache().setData(lock, out.toString(), modify);
                } catch (JAXBException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        @Override
        protected Object getModel() {

            Gpx gpx = null;
            try {
                gpx = getGpx();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
            }

            return gpx;
        }

        @Override
        protected void reloadModelFromData() {
            try {
                parseDocument();
            } catch (IOException ex) {
            }
        }

    }

}
