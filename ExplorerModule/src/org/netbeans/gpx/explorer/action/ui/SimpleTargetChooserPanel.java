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
 * Software is Sun Microsystems, Inc. Portions Copyright 2004-2007 Sun
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
 */

package org.netbeans.gpx.explorer.action.ui;

import java.awt.Component;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author  Petr Hrebejk
 */
//from org.netbeans.modules.project.ui
public final class SimpleTargetChooserPanel implements WizardDescriptor.Panel<WizardDescriptor>, ChangeListener {

    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private SimpleTargetChooserPanelGUI gui;

    //
    private DataFolder folder;
    private WizardDescriptor.Panel<WizardDescriptor> bottomPanel;
    private WizardDescriptor wizard;
    private boolean isFolder;
    private boolean freeFileExtension;

    @SuppressWarnings("LeakingThisInConstructor")
    public SimpleTargetChooserPanel( DataFolder folder,WizardDescriptor.Panel<WizardDescriptor> bottomPanel, boolean isFolder, boolean freeFileExtension) {
        this.folder = folder;
        //
        //
        //
        this.bottomPanel = bottomPanel;
        if ( bottomPanel != null ) {
            bottomPanel.addChangeListener( this );
        }
        this.isFolder = isFolder;
        this.freeFileExtension = freeFileExtension;
        this.gui = null;
    }

    public @Override Component getComponent() {
        if (gui == null) {
            gui = new SimpleTargetChooserPanelGUI( folder.getPrimaryFile(), bottomPanel == null ? null : bottomPanel.getComponent(), isFolder, freeFileExtension);
            gui.addChangeListener(this);
        }
        return gui;
    }

    public @Override HelpCtx getHelp() {
        
        HelpCtx help = null;
        if ( bottomPanel != null ) {
            help = bottomPanel.getHelp();
            
        }
        
        return help;
    }

    public @Override boolean isValid() {
        boolean ok = ( gui != null && gui.getTargetName() != null && /*gui.getTargetGroup() != null &&*/
               ( bottomPanel == null || bottomPanel.isValid() ) );
        
        if (!ok) {
            return false;
        }
        
        // check if the file name can be created
        FileObject template = Templates.getTemplate( wizard );
        String errorMessage = UIUtilities.canUseFileName (gui.getRootFolder(), gui.getTargetFolder(), gui.getTargetName(), template.getExt (), isFolder, freeFileExtension);
        wizard.putProperty (WizardDescriptor.PROP_ERROR_MESSAGE, errorMessage);

        return errorMessage == null;
    }

    public @Override void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    public @Override void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }
    
    public @Override void readSettings(WizardDescriptor settings) {
                
        wizard = settings;
                
        if ( gui == null ) {
            getComponent();
        }
        
        /*STARBEANS*/
        /* Callback on Wizard to change initial value of wizard*/
        String documentName = (String) wizard.getProperty("documentname")  ;
        
        // Try to preselect a folder            
        FileObject preselectedTarget = Templates.getTargetFolder( wizard );
        // Init values
        gui.initValues( Templates.getTemplate( wizard ), preselectedTarget, documentName);
        
        // XXX hack, TemplateWizard in final setTemplateImpl() forces new wizard's title
        // this name is used in NewFileWizard to modify the title
        Object substitute = gui.getClientProperty ("NewFileWizard_Title"); // NOI18N
        if (substitute != null) {
            wizard.putProperty ("NewFileWizard_Title", substitute); // NOI18N
        }
        
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_DATA, new String[] { // NOI18N
            NbBundle.getBundle (SimpleTargetChooserPanel.class).getString ("LBL_TemplatesPanel_Name"), // NOI18N
            NbBundle.getBundle (SimpleTargetChooserPanel.class).getString ("LBL_SimpleTargetChooserPanel_Name")}); // NOI18N
            
        if ( bottomPanel != null ) {
            bottomPanel.readSettings( settings );
        }
    }
    
    public @Override void storeSettings(WizardDescriptor settings) {
        if (WizardDescriptor.PREVIOUS_OPTION.equals(settings.getValue())) {
            return;
        }
        if(!settings.getValue().equals(WizardDescriptor.CANCEL_OPTION) && isValid()) {
            if ( bottomPanel != null ) {
                bottomPanel.storeSettings( settings );
            }
            
            String name = gui.getTargetName ();
            if (name.indexOf ('/') > 0) { // NOI18N
                name = name.substring (name.lastIndexOf ('/') + 1);
            }

            Templates.setTargetFolder(settings, getTargetFolderFromGUI());
            Templates.setTargetName(settings, name);
        }
        settings.putProperty("NewFileWizard_Title", null); // NOI18N
    }

    public @Override void stateChanged(ChangeEvent e) {
        changeSupport.fireChange();
    }
    
    private FileObject getTargetFolderFromGUI () {
    	//STARBEANS
        return gui.getRootFolder();
    }
}
