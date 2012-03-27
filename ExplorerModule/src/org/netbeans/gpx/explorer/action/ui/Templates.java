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
 * Software is Sun Microsystems, Inc. Portions Copyright 2004-2006 Sun
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

import java.io.IOException;
import org.openide.ErrorManager;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.TemplateWizard;


/**
 *
 * @author Jesse Glick
 */
public class Templates {
            
    public static final String WIZARD_KEY_TARGET_FOLDER = "targetFolder"; // NOI18N
    
    public static final String WIZARD_KEY_TARGET_NAME = "targetName"; // NOI18N
    
    public static final String WIZARD_KEY_TEMPLATE = "targetTemplate"; // NOI18N
    
    private Templates() {}
        
    /** Method to communicate current choice of template to a custom 
     * WizardIteartor associated with particular template.
     */
    public static FileObject getTemplate( WizardDescriptor wizardDescriptor ) {
        assert wizardDescriptor != null : "wizardDescriptor cannot be null.";
        if ( wizardDescriptor instanceof TemplateWizard ) {
            DataObject template = ((TemplateWizard)wizardDescriptor).getTemplate();
            if (template != null) {
                return template.getPrimaryFile();            
            }
        }
        return (FileObject) wizardDescriptor.getProperty( Templates.WIZARD_KEY_TEMPLATE );
    }
    
    /** Method to communicate current choice of target folder to a custom 
     * WizardIteartor associated with particular template.
     */
    public static FileObject getTargetFolder( WizardDescriptor wizardDescriptor ) {
        
        FileObject folder = null;
        if ( wizardDescriptor instanceof TemplateWizard ) {
            try {
                folder = ((TemplateWizard)wizardDescriptor).getTargetFolder().getPrimaryFile();
            }
            catch ( IOException e ) {
                ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
            }
        }
        else {
            folder = (FileObject) wizardDescriptor.getProperty( Templates.WIZARD_KEY_TARGET_FOLDER );
        }
        return folder;
    }
    
    /** Sets the target folder for given WizardDescriptor to be used from
     * custom target choosers
     */    
    public static void setTargetFolder( WizardDescriptor wizardDescriptor, FileObject folder ) {
        
        if ( wizardDescriptor instanceof TemplateWizard ) {            
            DataFolder dataFolder = DataFolder.findFolder( folder );            
            ((TemplateWizard)wizardDescriptor).setTargetFolder( dataFolder );
        }
        else {
            wizardDescriptor.putProperty( Templates.WIZARD_KEY_TARGET_FOLDER, folder );
        }
    }

    /** Method to communicate current choice of target name to a custom 
     * WizardIteartor associated with particular template.
     */
    public static String getTargetName( WizardDescriptor wizardDescriptor ) {
        
        String target = (String) wizardDescriptor.getProperty( Templates.WIZARD_KEY_TARGET_NAME );
        
        if ( wizardDescriptor instanceof TemplateWizard ) {
            target = ((TemplateWizard)wizardDescriptor).getTargetName();
        }
        return target;
    }
    
    /** Sets the target name for given WizardDescriptor to be used from
     * custom target choosers
     */
    public static void setTargetName( WizardDescriptor wizardDescriptor, String targetName ) {
        if ( wizardDescriptor instanceof TemplateWizard ) {                        
            ((TemplateWizard)wizardDescriptor).setTargetName( targetName );
        }
        else {
            wizardDescriptor.putProperty( Templates.WIZARD_KEY_TARGET_NAME, targetName );
        }
    }
}
