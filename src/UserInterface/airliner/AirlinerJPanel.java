/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.airliner;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import platform.TravelAgency;
import seller.Airliner;

/**
 *
 * @author jrwang8
 */
public class AirlinerJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AirlinerJPanel
     */
    
    TravelAgency travelAgency;
    JPanel userProcessContainer;
    
    public AirlinerJPanel(TravelAgency travelAgency, JPanel userProcessContainer) {
        initComponents();
        this.travelAgency = travelAgency;
        this.userProcessContainer = userProcessContainer;
        populateTable();
    }
    
    private void populateTable() {
        
        DefaultTableModel dtm = (DefaultTableModel)tblAirliner.getModel();
        dtm.setRowCount(0);
        
        for(Airliner airliner : travelAgency.getAirlinerList()) {
            Object[] row = new Object[3];
            row[0] = airliner;
            row[1] = airliner.getNumOfAirplane();
            row[2] = airliner.getNumOfFlight();
            
            dtm.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAirliner = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        tblAirliner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Airliner", "AirplaneNum", "FlightNum"
            }
        ));
        jScrollPane1.setViewportView(tblAirliner);

        btnCreate.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btnCreate.setText("Create Airliner");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDetail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btnDetail.setText("View Airliner");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("Airliner");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        
        CreateAirlinerJPanel panel = new CreateAirlinerJPanel(travelAgency, userProcessContainer);
        
        userProcessContainer.add("CreateAirlinerJPanel", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = tblAirliner.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Airliner airliner = (Airliner) tblAirliner.getValueAt(selectedRow, 0);
            DetailAirlinerJPanel panel = new DetailAirlinerJPanel(this.travelAgency, airliner, userProcessContainer);
            userProcessContainer.add("DetailAirlinerJPanel", panel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        
    }//GEN-LAST:event_btnDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDetail;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAirliner;
    // End of variables declaration//GEN-END:variables
}
