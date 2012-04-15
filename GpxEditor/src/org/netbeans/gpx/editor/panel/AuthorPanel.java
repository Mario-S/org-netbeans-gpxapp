package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Email;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.Person;
import javax.swing.JComponent;
import org.netbeans.gpx.editor.binding.converter.EmailConverter;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
public class AuthorPanel extends AbstractMetadataPanel {

    private Person person;
    private EmailConverter emailConverter;

    public AuthorPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView, gpxDataObject);

        emailConverter = new EmailConverter();

        Metadata metadata = getMetadata();
        if (metadata != null) {
            this.person = metadata.getAuthor();
        }

        initComponents();
        setValues();
        addModifiers();
    }

    private void addModifiers() {
        addModifier(txtAuthorName);
        addModifier(txtAuthorEmail);
    }

    private void setValues() {

        if (person != null) {
            txtAuthorName.setText(person.getName());
            Email email = person.getEmail();
            if (email != null) {
                txtAuthorEmail.setText(emailConverter.convertForward(email));
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAuthorName = new javax.swing.JTextField();
        txtAuthorEmail = new javax.swing.JTextField();
        lblAuthorEmail = new javax.swing.JLabel();
        lblAuthorName = new javax.swing.JLabel();
        lblAuthorLink = new javax.swing.JLabel();

        lblAuthorEmail.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorEmail.text")); // NOI18N

        lblAuthorName.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorName.text")); // NOI18N

        lblAuthorLink.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorLink.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(lblAuthorName)
                                .add(23, 23, 23))
                            .add(layout.createSequentialGroup()
                                .add(lblAuthorEmail)
                                .add(18, 18, 18)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(txtAuthorEmail)
                            .add(txtAuthorName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(lblAuthorLink))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(lblAuthorName))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(txtAuthorName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtAuthorEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(lblAuthorEmail))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblAuthorLink)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAuthorEmail;
    private javax.swing.JLabel lblAuthorLink;
    private javax.swing.JLabel lblAuthorName;
    private javax.swing.JTextField txtAuthorEmail;
    private javax.swing.JTextField txtAuthorName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setValue(JComponent source, Object value) {

        if (person == null) {
            person = new Person();
        }

        if (source == txtAuthorName) {
            person.setName((String) value);
        } else if (source == txtAuthorEmail) {
            Email email = emailConverter.convertReverse((String) value);
            person.setEmail(email);
        }
    }

    @Override
    protected void merge() {
        Metadata metadata = checkMetadata();
        metadata.setAuthor(person);
        super.merge();
    }

    @Override
    public void linkButtonPressed(Object ddBean, String ddProperty) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JComponent getErrorComponent(String errorId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
