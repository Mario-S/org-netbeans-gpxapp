/*
 * $$LastChangedRevision: 1 $$
 * $$LastChangedBy: msc $$ 
 * 
 */

/*
 * MetadataPanel.java
 *
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Link;
import com.topografix.gpx.model.Metadata;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.ListModel;
import org.netbeans.gpx.editor.binding.converter.XMLGregorianCalendarConverter;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
public class MetadataPanel extends AbstractMetadataPanel {

    private XMLGregorianCalendarConverter calendarConverter;
    
    private LinksListModel listModel;

    /** Creates new form MetadataPanel */
    public MetadataPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView, gpxDataObject);

        calendarConverter = new XMLGregorianCalendarConverter();

        listModel = new LinksListModel();

        initComponents();

        addModifiers();

        setValues();
    }

    private void addModifiers() {

        addModifier(txtName);
        addModifier(txtTime);
        addModifier(txtKeywords);
        addModifier(txtAreaDesc);
    }

    private void setValues() {
        Metadata metadata = getMetadata();
        if (metadata != null) {
            txtName.setText(metadata.getName());
            txtTime.setText(calendarConverter.convertForward(metadata.getTime()));
            txtKeywords.setText(metadata.getKeywords());
            txtAreaDesc.setText(metadata.getDesc());

            List<Link> links = metadata.getLinks();
            if (!links.isEmpty()) {
                listModel.setLinks(links);
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

        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblDesc = new javax.swing.JLabel();
        scrollDesc = new javax.swing.JScrollPane();
        txtAreaDesc = new javax.swing.JTextArea();
        lblTime = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        lblKeywords = new javax.swing.JLabel();
        txtKeywords = new javax.swing.JTextField();
        lblLinks = new javax.swing.JLabel();
        scrollLinks = new javax.swing.JScrollPane();
        lstLinks = new javax.swing.JList();
        btnAddLink = new javax.swing.JButton();
        btnRemoveLink = new javax.swing.JButton();
        btnEditLink = new javax.swing.JButton();

        lblName.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.lblName.text")); // NOI18N

        lblDesc.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.lblDesc.text")); // NOI18N

        txtAreaDesc.setColumns(20);
        txtAreaDesc.setRows(5);
        scrollDesc.setViewportView(txtAreaDesc);

        lblTime.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.lblTime.text")); // NOI18N

        lblKeywords.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.lblKeywords.text")); // NOI18N

        lblLinks.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.lblLinks.text")); // NOI18N

        lstLinks.setModel(listModel);
        scrollLinks.setViewportView(lstLinks);

        btnAddLink.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.btnAddLink.text")); // NOI18N

        btnRemoveLink.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.btnRemoveLink.text")); // NOI18N
        btnRemoveLink.setEnabled(false);

        btnEditLink.setText(org.openide.util.NbBundle.getMessage(MetadataPanel.class, "MetadataPanel.btnEditLink.text")); // NOI18N
        btnEditLink.setEnabled(false);
        btnEditLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLinkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKeywords)
                            .addComponent(lblTime)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtTime)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLinks))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollDesc)
                            .addComponent(scrollLinks, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddLink)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveLink)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditLink)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKeywords)
                    .addComponent(txtKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDesc)
                    .addComponent(scrollDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLinks)
                    .addComponent(scrollLinks, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLink)
                    .addComponent(btnRemoveLink)
                    .addComponent(btnEditLink))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditLinkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLink;
    private javax.swing.JButton btnEditLink;
    private javax.swing.JButton btnRemoveLink;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblKeywords;
    private javax.swing.JLabel lblLinks;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTime;
    private javax.swing.JList lstLinks;
    private javax.swing.JScrollPane scrollDesc;
    private javax.swing.JScrollPane scrollLinks;
    private javax.swing.JTextArea txtAreaDesc;
    private javax.swing.JTextField txtKeywords;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setValue(JComponent source, Object o) {
        Metadata metadata = checkMetadata();
        if (source == txtName) {
            metadata.setName((String) o);
        } else if (source == txtTime) {
            metadata.setTime(calendarConverter.convertReverse((String) o));
        } else if (source == txtKeywords) {
            metadata.setKeywords((String) o);
        } else if (source == txtAreaDesc) {
            metadata.setDesc((String) o);
        }
    }

    @Override
    public void linkButtonPressed(Object o, String string) {
        //TODO implement
    }

    @Override
    public JComponent getErrorComponent(String string) {
        return null;
    }

    private class LinksListModel extends AbstractListModel {

        private List<Link> links;

        public LinksListModel() {
            links = new ArrayList<Link>();
        }

        List<Link> getLinks() {
            return links;
        }

        void setLinks(List<Link> links) {
            this.links = links;
        }

        @Override
        public int getSize() {
            return links.size();
        }

        @Override
        public Object getElementAt(int i) {
            return links.get(i);
        }
    }
}
