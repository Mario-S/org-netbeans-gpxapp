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
 *
 * Portions Copyrighted 2008-2010 George Waldon
 */
package org.netbeans.gpx.explorer.action.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.ContextAwareAction;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/** The util methods for ui.
 * @see org.netbeans.modules.project.ui.ProjectUtilities in projectui
 * @author  Jiri Rechtacek
 */
public class UIUtilities {

    /** Creates a new instance of CloseAllProjectDocuments */
    private UIUtilities() {
    }

    /** Invokes the preferred action on given object and tries to select it in
     * corresponding view, e.g. in logical view if possible otherwise
     * in physical project's view.
     * Note: execution this methods can invokes new threads to assure the action
     * is called in EQ.
     *
     * @param newDo new data object
     */
    public static void openAndSelectNewObject(final DataObject newDo) {
        // call the preferred action on main class
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                final Node node = newDo.getNodeDelegate();
                Action action = node.getPreferredAction();
                Lookup lookUp = node.getLookup();
                if (action instanceof ContextAwareAction) {
                    action = ((ContextAwareAction) action).createContextAwareInstance(lookUp);
                }
                if (action != null) {
                    action.actionPerformed(new ActionEvent(node, ActionEvent.ACTION_PERFORMED, "")); // NOI18N
                }

                Lookup lu = Lookups.forPath("Actions/Window/SelectDocumentNode");
                ContextAwareAction c = lu.lookup(ContextAwareAction.class);
                c.actionPerformed(new ActionEvent(node, ActionEvent.ACTION_PERFORMED, ""));

            }
        });
    }

    /** Checks if the given file name can be created in the target folder.
     *
     * @param targetFolder target folder (e.g. source group)
     * @param folderName name of the folder relative to target folder (null or /-separated)
     * @param newObjectName name of created file
     * @param extension extension of created file
     * @param allowFileSeparator if '/' (and possibly other file separator, see {@link FileUtil#createFolder FileUtil#createFolder})
     *                           is allowed in the newObjectName
     * @return localized error message or null if all right
     */
    public static String canUseFileName(FileObject targetFolder, String folderName, String newObjectName,
            String extension, boolean allowFileSeparator, boolean freeFileExtension) {
        assert newObjectName != null; // SimpleTargetChooserPanel.isValid returns false if it is... XXX should it use an error label instead?

        boolean allowSlash = false;
        boolean allowBackslash = false;
        int errorVariant = 0;

        if (allowFileSeparator) {
            if (File.separatorChar == '\\') {
                errorVariant = 3;
                allowSlash = allowBackslash = true;
            } else {
                errorVariant = 1;
                allowSlash = true;
            }
        }

        if ((!allowSlash && newObjectName.indexOf('/') != -1) || (!allowBackslash && newObjectName.indexOf('\\') != -1)) {
            //if errorVariant == 3, the test above should never be true:
            assert errorVariant == 0 || errorVariant == 1 : "Invalid error variant: " + errorVariant;

            return NbBundle.getMessage(UIUtilities.class, "MSG_not_valid_filename", newObjectName, new Integer(errorVariant));
        }

        // test whether the selected folder on selected filesystem already exists
        if (targetFolder == null) {
            return NbBundle.getMessage(UIUtilities.class, "MSG_fs_or_folder_does_not_exist"); // NOI18N
        }

        // target directory should be writable
        File targetDir = folderName != null ? new File(FileUtil.toFile(targetFolder), folderName) : FileUtil.toFile(targetFolder);
        if (targetDir != null) {
            if (targetDir.exists() && !targetDir.canWrite()) {
                return NbBundle.getMessage(UIUtilities.class, "MSG_fs_is_readonly"); // NOI18N
            }
        } else if (!targetFolder.canWrite()) {
            return NbBundle.getMessage(UIUtilities.class, "MSG_fs_is_readonly"); // NOI18N
        }

        // file should not already exist
        StringBuilder relFileName = new StringBuilder();
        if (folderName != null) {
            if (!allowBackslash && folderName.indexOf('\\') != -1) {
                return NbBundle.getMessage(UIUtilities.class, "MSG_not_valid_folder", folderName, new Integer(1));
            }
            relFileName.append(folderName);
            relFileName.append('/');
        }
        relFileName.append(newObjectName);
        String ext = "";
        if (extension != null && extension.length() != 0 && (!freeFileExtension || newObjectName.indexOf('.') == -1)) {
            ext = "." + extension;
            relFileName.append(ext);
        }
        if (targetFolder.getFileObject(relFileName.toString()) != null) {
            return NbBundle.getMessage(UIUtilities.class, "MSG_file_already_exist", newObjectName + ext); // NOI18N
        }

        // all ok
        return null;
    }

    public static class WaitCursor implements Runnable {

        private boolean show;

        private WaitCursor(boolean show) {
            this.show = show;
        }

        public static void show() {
            invoke(new WaitCursor(true));
        }

        public static void hide() {
            invoke(new WaitCursor(false));
        }

        private static void invoke(WaitCursor wc) {
            if (GraphicsEnvironment.isHeadless()) {
                return;
            }
            if (SwingUtilities.isEventDispatchThread()) {
                wc.run();
            } else {
                SwingUtilities.invokeLater(wc);
            }
        }

        @Override
        public void run() {
            JFrame f = (JFrame) WindowManager.getDefault().getMainWindow();
            Component componet = f.getGlassPane();
            componet.setVisible(show);
            componet.setCursor(show ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : null);
        }
    }
}
