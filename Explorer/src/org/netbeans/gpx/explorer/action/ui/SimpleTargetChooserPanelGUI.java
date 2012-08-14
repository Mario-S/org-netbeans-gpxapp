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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.NotifyDescriptor;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;
import org.netbeans.gpx.explorer.FavoritesActions;

/**
 *
 * @author  phrebejk
 */
public class SimpleTargetChooserPanelGUI extends javax.swing.JPanel implements ActionListener, DocumentListener {
  
    /** preferred dimension of the panels */
    private static final Dimension PREF_DIM = new Dimension(500, 340);
    
    private static final String NEW_FILE_PREFIX = 
        NbBundle.getMessage( SimpleTargetChooserPanelGUI.class, "LBL_SimpleTargetChooserPanelGUI_NewFilePrefix" ); // NOI18N
    
    private final ListCellRenderer CELL_RENDERER = new GroupCellRenderer();
        
    private String expectedExtension;
    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private FileObject folder;
    private boolean isFolder;
    private boolean freeFileExtension;
    
    /** Used to keep current dir from JFileChooser for Add to Favorites action
     * on root node. */
    private static File currentDir = null;
    
    /** Creates new form SimpleTargetChooserGUI */
    @SuppressWarnings("LeakingThisInConstructor")
    public SimpleTargetChooserPanelGUI( FileObject folder, Component bottomPanel, boolean isFolder, boolean freeFileExtension) {
        assert folder.isFolder();
        this.folder=folder;
        currentDir = FileUtil.toFile(folder);
        this.isFolder = isFolder;
        this.freeFileExtension = freeFileExtension;
        initComponents();
        
        locationComboBox.setRenderer( CELL_RENDERER );
        
        if ( bottomPanel != null ) {
            bottomPanelContainer.add( bottomPanel, java.awt.BorderLayout.CENTER );
        }
        initValues( null, null, null );
        
        setPreferredSize(PREF_DIM);

        browseButton.addActionListener( this );
        locationComboBox.addActionListener( this );
        documentNameTextField.getDocument().addDocumentListener( this );
        folderTextField.getDocument().addDocumentListener( this );
        
        setName (NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_SimpleTargetChooserPanel_Name")); // NOI18N
    }
    
    final void initValues(FileObject template, FileObject preselectedFolder, String documentName) {
        assert folder != null;

          
//        projectTextField.setText("text " /*ProjectUtils.getInformation(project).getDisplayName()*/);
        
//        Sources sources = ProjectUtils.getSources( project );
//                        
//        if (folders == null) {
//            folders = sources.getSourceGroups( Sources.TYPE_GENERIC );
//        }
//        
//        if ( folders.length < 2 ) {
//            // one source group i.e. hide Location
            locationLabel.setVisible( false );
            locationComboBox.setVisible( false );
//        }
//        else {
            // more source groups user needs to select location
//            locationLabel.setVisible( true );
//            locationComboBox.setVisible( true );
            
//        }
        
//        locationComboBox.setModel( new DefaultComboBoxModel( folders ) );
        // Guess the group we want to create the file in
//        SourceGroup preselectedGroup = getPreselectedGroup( folders, preselectedFolder );
        // Create OS dependent relative name
//        if (preselectedGroup != null) {
//            locationComboBox.setSelectedItem( preselectedGroup );
//            FileObject rootFolder = preselectedGroup.getRootFolder();
//            if (rootFolder == null) {
//                throw new NullPointerException("#173645: null returned illegally from " + preselectedGroup.getClass().getName() + ".getRootFolder()");
//            }
//            folderTextField.setText(getRelativeNativeName(rootFolder, preselectedFolder));
//        }
        
        String path = FileUtil.getFileDisplayName(folder);
        folderTextField.setText( path.replace( '/', File.separatorChar ) ); // NOI18N
        
        String ext = template == null ? "" : template.getExt(); // NOI18N
        expectedExtension = ext.length() == 0 ? "" : "." + ext; // NOI18N
        
        String displayName = null;
        try {
            if (template != null) {
                DataObject templateDo = DataObject.find (template);
                displayName = templateDo.getNodeDelegate ().getDisplayName ();
            }
        } catch (DataObjectNotFoundException ex) {
            displayName = template.getName ();
        }
        putClientProperty ("NewFileWizard_Title", displayName);// NOI18N        
        if (template != null) {
            final String baseName = NEW_FILE_PREFIX + template.getName ();
            if (documentName == null) {
                documentName = baseName;
                /*STARBEANS*/
                if(template.getName ()!=null && template.getName ().length()!=0)
                    documentName = template.getName();
            }
            if (preselectedFolder != null) {
                int index = 0;
                while (true) {
                    FileObject _tmp = preselectedFolder.getFileObject(documentName, template.getExt());
                    if (_tmp == null) {
                        break;
                    }
                    documentName = baseName + ++index;
                }
            }

            documentNameTextField.setText (documentName);
            documentNameTextField.selectAll ();
        }

        if (isFolder) {
            Mnemonics.setLocalizedText(jLabel3, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_FolderName_Label")); // NOI18N
            Mnemonics.setLocalizedText(jLabel2, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_ParentFolder_Label")); // NOI18N
            Mnemonics.setLocalizedText(jLabel4, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_CreatedFolder_Label")); // NOI18N
        } else {
            Mnemonics.setLocalizedText(jLabel3, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_FileName_Label")); // NOI18N
            Mnemonics.setLocalizedText(jLabel2, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Folder_Label")); // NOI18N
            Mnemonics.setLocalizedText(jLabel4, NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_CreatedFile_Label")); // NOI18N
        }
    }
    
    //STARBEANS
    public FileObject getRootFolder() {
        return folder;
    }
            
    public String getTargetFolder() {
        
        String folderName = folderTextField.getText().trim();
        
        if ( folderName.length() == 0 ) {
            return null;
        }
        else {           
            return folderName.replace( File.separatorChar, '/' ); // NOI18N
        }
    }
    
    public String getTargetName() {
        
        String text = documentNameTextField.getText().trim();
        
        if ( text.length() == 0 ) {
            return null;
        }
        else {
            return text;
        }
    }
    
    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }
    
    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        documentNameTextField = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        locationComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        folderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        targetSeparator = new javax.swing.JSeparator();
        bottomPanelContainer = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel3.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "MNE_TargetChooser_FileName_Label").charAt(0));
        jLabel3.setLabelFor(documentNameTextField);
        jLabel3.setText(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_FileName_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(documentNameTextField, gridBagConstraints);
        documentNameTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_documentNameTextField")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 24, 0);
        add(jPanel1, gridBagConstraints);

        locationLabel.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "MNE_TargetChooser_Location_Label").charAt(0));
        locationLabel.setLabelFor(locationComboBox);
        locationLabel.setText(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Location_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(locationLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 5, 0);
        add(locationComboBox, gridBagConstraints);
        locationComboBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_locationComboBox")); // NOI18N

        jLabel2.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "MNE_TargetChooser_ParentFolder_Label").charAt(0));
        jLabel2.setLabelFor(folderTextField);
        jLabel2.setText(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Folder_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(folderTextField, gridBagConstraints);
        folderTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_folderTextField")); // NOI18N

        browseButton.setMnemonic(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "MNE_TargetChooser_Browse_Button").charAt(0));
        browseButton.setText(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Browse_Button")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(browseButton, gridBagConstraints);
        browseButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_browseButton")); // NOI18N

        jLabel4.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "MNE_TargetChooser_CreatedFile_Label").charAt(0));
        jLabel4.setLabelFor(fileTextField);
        jLabel4.setText(org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_CreatedFile_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel4, gridBagConstraints);

        fileTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(fileTextField, gridBagConstraints);
        fileTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_fileTextField")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(targetSeparator, gridBagConstraints);

        bottomPanelContainer.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        add(bottomPanelContainer, gridBagConstraints);

        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_SimpleTargetChooserPanelGUI")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanelContainer;
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField documentNameTextField;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField folderTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox locationComboBox;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JSeparator targetSeparator;
    // End of variables declaration//GEN-END:variables

//    private SourceGroup getPreselectedGroup( SourceGroup[] groups, FileObject folder ) {        
//        for( int i = 0; folder != null && i < groups.length; i++ ) {
//            if( FileUtil.isParentOf( groups[i].getRootFolder(), folder ) ) {
//                return groups[i];
//            }
//        }
//        return groups[0];
//    }
    
//    private String getRelativeNativeName( FileObject root, FileObject folder ) {
//        assert root != null;
//        
//        String path;
//        
//        if (folder == null) {
//            path = ""; // NOI18N
//        }
//        else {
//            path = FileUtil.getRelativePath( root, folder );            
//        }
//        
//        return path == null ? "" : path.replace( '/', File.separatorChar ); // NOI18N
//    }
    
    private void updateCreatedFolder() {
        
        /*FileObject root = folder;*/
        
        String folderName = folderTextField.getText().trim();
        String documentName = documentNameTextField.getText().trim();
        
        String createdFileName = /*FileUtil.getFileDisplayName( root ) + */
            ( folderName.startsWith("/") || folderName.startsWith( File.separator ) ? "" : "/" ) + // NOI18N
            folderName + 
            ( folderName.endsWith("/") || folderName.endsWith( File.separator ) || folderName.length() == 0 ? "" : "/" ) + // NOI18N
            documentName + (!freeFileExtension || documentName.indexOf('.') == -1 ? expectedExtension : "");
            
        fileTextField.setText( createdFileName.replace( '/', File.separatorChar ) ); // NOI18N    
            
        changeSupport.fireChange();
    }
    
   
    // ActionListener implementation -------------------------------------------
    
    public @Override void actionPerformed(ActionEvent e) {
        if ( browseButton == e.getSource() ) {
            FileObject fo=null;
            // Show the browse dialog
            //
            //
            //
            fo = chooseFileObject();
            if (fo == null) return;
            try {
                DataObject createdDO = DataObject.find(fo);
                FavoritesActions.Add.selectAfterAddition(createdDO);
            } catch (DataObjectNotFoundException donfe) {
                ErrorManager.getDefault().notify(donfe);  
            }
            if ( fo != null && fo.isFolder() ) {
                String path = FileUtil.getFileDisplayName(fo);
                folderTextField.setText( path.replace( '/', File.separatorChar ) ); // NOI18N
                folder = fo;
            }                        
        }
        else if ( locationComboBox == e.getSource() )  {
            updateCreatedFolder();
        }
    } 
    
    /**
     *
     * @return FileObject or null if FileChooser dialog is canceled
     */
    public static FileObject chooseFileObject() {
        FileObject retVal = null;
        File chooserSelection = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        chooser.setDialogTitle(NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("CTL_DialogTitle"));
        chooser.setApproveButtonText(NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("CTL_ApproveButtonText"));
        if (currentDir != null) {
            chooser.setCurrentDirectory(currentDir);
        }
        int option = chooser.showOpenDialog( WindowManager.getDefault().getMainWindow() ); // Show the chooser
        if ( option == JFileChooser.APPROVE_OPTION ) {
            chooserSelection = chooser.getSelectedFile();
            File selectedFile = FileUtil.normalizeFile(chooserSelection);
            //Workaround for JDK bug #5075580 (filed also in IZ as #46882)
            if (!selectedFile.exists()) {
                if ((selectedFile.getParentFile() != null) && selectedFile.getParentFile().exists()) {
                    if (selectedFile.getName().equals(selectedFile.getParentFile().getName())) {
                        selectedFile = selectedFile.getParentFile();
                    }
                }
            }
            //#50482: Check if selected file exists eg. user can enter any file name to text box.
            if (!selectedFile.exists()) {
                String message = NbBundle.getMessage(SimpleTargetChooserPanelGUI.class,"ERR_FileDoesNotExist",selectedFile.getPath());
                String title = NbBundle.getMessage(SimpleTargetChooserPanelGUI.class,"ERR_FileDoesNotExistDlgTitle");
                DialogDisplayer.getDefault().notify
                        (new NotifyDescriptor(message,title,NotifyDescriptor.DEFAULT_OPTION,
                        NotifyDescriptor.INFORMATION_MESSAGE, new Object[] { NotifyDescriptor.CLOSED_OPTION },
                        NotifyDescriptor.OK_OPTION));
            } else {
                retVal = FileUtil.toFileObject(selectedFile);
                assert retVal != null;
            }
        }
        currentDir = chooser.getCurrentDirectory();
        return retVal;
    }
    
    // DocumentListener implementation -----------------------------------------
    
    public @Override void changedUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }    
    
    public @Override void insertUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }
    
    public @Override void removeUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }
    
    
    // Rendering of the location combo box -------------------------------------
    
    private class GroupCellRenderer extends JLabel implements ListCellRenderer {
    
        public GroupCellRenderer() {
            setOpaque( true );
        }
        
        public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
            setText( value.toString () );
            setIcon( null );

            if ( isSelected ) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());             
            }
            else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
             
            }
            return this;        
        }
                
    }
}
