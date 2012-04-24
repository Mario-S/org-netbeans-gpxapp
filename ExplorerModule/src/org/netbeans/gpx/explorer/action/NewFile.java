/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or sbbuild/licenses/cddl-gpl-2-cp.txt. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * sbbuild/licenses/cddl-gpl-2-cp.txt.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2009 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Portions Copyrighted 2008-2010 George Waldon
 */
package org.netbeans.gpx.explorer.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.openide.ErrorManager;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.Presenter.Popup;
import org.openide.util.ContextAwareAction;
import org.openide.util.actions.Presenter;
import org.openide.awt.Mnemonics;
import org.openide.util.ImageUtilities;

import org.netbeans.gpx.explorer.action.ui.UIUtilities;
import org.netbeans.gpx.explorer.action.ui.NewFileWizard;

/** Action for invoking the project sensitive NewFile Wizard
 */
public class NewFile extends LookupSensitiveAction implements PropertyChangeListener,
        Popup, PopupMenuListener, Presenter.Menu, ContextAwareAction {

    private static final String ICON_PATH = "org/netbeans/gpx/explorer/resources/newFile.gif";
    private static final String EMPTY_ICON_PATH = "org/netbeans/gpx/explorer/resources/empty.gif";
    private static final Icon ICON = ImageUtilities.loadImageIcon(ICON_PATH, false); //NOI18N
    private static final String _NAME = NbBundle.getMessage(NewFile.class, "LBL_NewFileAction_Name");
    private static final String _SHORT_DESCRIPTION = NbBundle.getMessage(NewFile.class, "LBL_NewFileAction_Tooltip");
    private static final String TEMPLATE_NAME_FORMAT = NbBundle.getMessage(NewFile.class, "LBL_NewFileAction_Template_PopupName");
    private static DataObject[] templates;
    private String name;
    private JMenuItem menuPresenter;
    private JMenu subMenu;

    public NewFile() {
        this( null );
    }

    public NewFile(Lookup context) {
        super(ICON, context, new Class[]{DataObject.class});
        this.name = _NAME;
        setDisplayName(name);
        putValue("iconBase", ICON_PATH); //NOI18N
        putValue(SHORT_DESCRIPTION, _SHORT_DESCRIPTION);
        refresh(getLookup());
    }

    @Override
    protected void refresh(Lookup context) {
        DataFolder preselectedFolder = preselectedFolder(context);
        if (preselectedFolder == null) {
            setEnabled(false);
            return;
        }
        FileObject fo = preselectedFolder.getPrimaryFile();
        if (fo == null) {
            setEnabled(false);
            return;
        }

        try {
            org.openide.filesystems.FileSystem fs = fo.getFileSystem();
            if (fs.isDefault()) {
                setEnabled(false);
                return;
            }
        } catch (Exception e) {
            setEnabled(false);
            return;
        }
        File fileForFolder = FileUtil.toFile(fo);
        if (fileForFolder == null || !fileForFolder.exists()) {
            setEnabled(false);
            return;
        }
        setEnabled(preselectedFolder != null);
        setDisplayName(_NAME);
    }

    @Override
    protected void actionPerformed( Lookup context ) {
        doPerform(context, null);
    }

    private void doPerform(Lookup context, DataObject template) {

        NewFileWizard wd = new NewFileWizard();

        DataFolder preselectedFolder = preselectedFolder(context);
        if (preselectedFolder != null) {
            wd.setTargetFolder(preselectedFolder);
        }

        try {
            Set resultSet = template == null ? wd.instantiate() : wd.instantiate(template);

            if (resultSet == null || resultSet.isEmpty ()) {
                // no new object, no work
                return ;
            }

            Iterator it = resultSet.iterator ();

            while (it.hasNext ()) {
                Object obj = it.next ();
                DataObject newDO = null;
                if (obj instanceof DataObject) {
                    newDO = (DataObject) obj;
                } else if (obj instanceof FileObject) {
                    try {
                        newDO = DataObject.find ((FileObject) obj);
                    } catch (DataObjectNotFoundException x) {
                        // XXX
                        assert false : obj;
                    }
                } else {
                    assert false : obj;
                }
                if (newDO != null) {
                    UIUtilities.openAndSelectNewObject (newDO);
                }
            }
        } catch (IOException e) {
            ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
        }
    }


    // Context Aware action implementation -------------------------------------

    @Override
    public Action createContextAwareInstance( Lookup actionContext ) {
        return new NewFile( actionContext );
    }

    // Presenter.Popup implementation ------------------------------------------
    @Override
    public JMenuItem getPopupPresenter() {
        /*STARBEANS*/
        return getMenuPresenter();
    }

    private void fillSubMenu() {
        fillSubMenu(subMenu);
    }

    // Private methods ---------------------------------------------------------
    private DataFolder preselectedFolder(Lookup context) {

        DataFolder preselectedFolder = null;

        // Try to find selected folder
        preselectedFolder = context.lookup(DataFolder.class);
        if ( preselectedFolder == null ) {
            // No folder selectd try with DataObject
            DataObject dobj = context.lookup(DataObject.class);
            if ( dobj != null) {
                // DataObject found => we'll use the parent folder
                preselectedFolder = dobj.getFolder();
            }
        }

        return preselectedFolder;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        refresh( Lookup.EMPTY );
    }
    public static String TEMPLATE_PROPERTY = "org.starbeans.modules.corelite.actions.NewFile.Template"; //NOI18N

    private void fillSubMenu(JMenu menuItem) {
        menuItem.removeAll();

        ActionListener menuListener = new PopupListener();

        DataFolder preselectedFolder = preselectedFolder(getLookup());

        boolean canWrite;
        if (preselectedFolder == null) {
            canWrite = false;
        }
        else {
            FileObject pf = preselectedFolder.getPrimaryFile();
            canWrite = pf != null && pf.canWrite();
        }

        DataObject ltemplates[] = getTemplates();
        for(int i = 0; i < ltemplates.length; i++ ) {
            Node n = ltemplates[i].getNodeDelegate();
            JMenuItem item = new JMenuItem(
                    MessageFormat.format( TEMPLATE_NAME_FORMAT, new Object[]{n.getDisplayName()}),
                    new ImageIcon( n.getIcon( BeanInfo.ICON_COLOR_16x16)));
            item.addActionListener( menuListener );
            item.putClientProperty( TEMPLATE_PROPERTY, ltemplates[i] );

            item.setEnabled( canWrite );
            menuItem.add( item );
        }
    }

    /**
     * returns all available templates
     * @return
     */
    private DataObject[] getTemplates() {

        if (templates == null) {

            ArrayList<DataObject> tList = new ArrayList<DataObject>(2);
            DataObject template;

            template = findTemplate("Templates/Other/file"); //NOI18N
            if (template != null) {
                tList.add(template);
            }

            template = findTemplate("Templates/Other/Folder"); //NOI18N
            if (template != null) {
                tList.add(template);
            }

            templates = new DataObject[tList.size()];
            tList.toArray(templates);
        }
        return templates;
    }

    /**
     * Try to find a {@link DataObject} by name
     * @param name of the DataObject
     * @return
     */
    private static DataObject findTemplate(String name) {
        FileObject tFo = FileUtil.getConfigFile(name);
        if (tFo == null) {
            return null;
        }
        try {
            return DataObject.find(tFo);
        } catch (DataObjectNotFoundException e) {
            return null;
        }

    }

    private class PopupListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem) e.getSource();
            DataObject template = (DataObject) source.getClientProperty(TEMPLATE_PROPERTY);
            doPerform(getLookup(), template);
        }
    }

    // Implementation of PopupMenuListener -------------------------------------
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        fillSubMenu();
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    // Implementation of Presenter.Menu ----------------------------------------
    @Override
    public JMenuItem getMenuPresenter() {

        if (menuPresenter == null) {
            menuPresenter = new JMenuItem(this);

            Icon icon = null;
            // ignore icon if noIconInMenu flag is set
            if (!Boolean.TRUE.equals(getValue("noIconInMenu"))) { //NOI18N
                icon = (Icon) getValue(Action.SMALL_ICON);
            }
            if (icon == null) {
                icon = new ImageIcon(ImageUtilities.loadImage(EMPTY_ICON_PATH)); //NOI18N
            }
            Mnemonics.setLocalizedText(menuPresenter, name);
            menuPresenter.setIcon(icon);
        }
        return menuPresenter;
    }
}
    
    
    