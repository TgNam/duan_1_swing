package views;

import javax.swing.JPanel;


/**
 *
 * @author TgNam
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private JPanel panel;

    public Main() {
        initComponents();
//        tesst t = new tesst();
//        panel = new Product();
//        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
//        tblCN.removeAll();
//        tblCN.add(panel);
//        tblCN.validate();
       
        this.setExtendedState(this.MAXIMIZED_BOTH);
        LoginJDialog login = new LoginJDialog(this, true);
        login.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tblCN = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnProduct = new javax.swing.JButton();
        btnReturns = new javax.swing.JButton();
        btnExchange = new javax.swing.JButton();
        btnCline = new javax.swing.JButton();
        btnStaff = new javax.swing.JButton();
        btnBuild = new javax.swing.JButton();
        btnDelivery_notes = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        bthThongke = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblCN.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout tblCNLayout = new javax.swing.GroupLayout(tblCN);
        tblCN.setLayout(tblCNLayout);
        tblCNLayout.setHorizontalGroup(
            tblCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 978, Short.MAX_VALUE)
        );
        tblCNLayout.setVerticalGroup(
            tblCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        btnProduct.setText("San pham");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        btnReturns.setText("Tra Hang");
        btnReturns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnsActionPerformed(evt);
            }
        });

        btnExchange.setText("Doi hang");
        btnExchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExchangeActionPerformed(evt);
            }
        });

        btnCline.setText("Khach Hang");
        btnCline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClineActionPerformed(evt);
            }
        });

        btnStaff.setText("Nhan vien");
        btnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffActionPerformed(evt);
            }
        });

        btnBuild.setText("Hoa don");
        btnBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuildActionPerformed(evt);
            }
        });

        btnDelivery_notes.setText("Phieu giao hang");
        btnDelivery_notes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelivery_notesActionPerformed(evt);
            }
        });

        btnSale.setText("Dot giam gia");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });

        btnVoucher.setText("Phieu giam gia");
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        bthThongke.setText("Thống kê");
        bthThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthThongkeActionPerformed(evt);
            }
        });

        jButton1.setText("Quản Lý Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExchange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelivery_notes, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(btnSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bthThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProduct)
                .addGap(18, 18, 18)
                .addComponent(btnReturns)
                .addGap(18, 18, 18)
                .addComponent(btnExchange)
                .addGap(18, 18, 18)
                .addComponent(btnCline)
                .addGap(18, 18, 18)
                .addComponent(btnStaff)
                .addGap(18, 18, 18)
                .addComponent(btnBuild)
                .addGap(18, 18, 18)
                .addComponent(btnDelivery_notes)
                .addGap(18, 18, 18)
                .addComponent(btnSale)
                .addGap(18, 18, 18)
                .addComponent(btnVoucher)
                .addGap(18, 18, 18)
                .addComponent(bthThongke)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
////        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new Product();
        
//        Product_stop_sellingJdialog pss = new Product_stop_sellingJdialog(this,true);
//        pss.setPannel(product);

        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnReturnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnsActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new ReturnsJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnReturnsActionPerformed

    private void btnExchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExchangeActionPerformed

//        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();

        panel = new ExchangeJpanel(this);
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnExchangeActionPerformed

    private void btnClineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClineActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new UserJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnClineActionPerformed

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new EMPLOYEEJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new BillJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnBuildActionPerformed

    private void btnDelivery_notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelivery_notesActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new Delivery_notesJpanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnDelivery_notesActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new SaleProductJpanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnSaleActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new VoucherJpanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void bthThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthThongkeActionPerformed
        // TODO add your handling code here:
        tblCN.removeAll();
        tblCN.repaint();
        panel = new StatisticsJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_bthThongkeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tblCN.removeAll();
        tblCN.repaint();
        panel = new InvoiceManagementJPanel();
        panel.setSize(tblCN.getWidth(), tblCN.getHeight());
        tblCN.removeAll();
        tblCN.add(panel);
        tblCN.validate();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bthThongke;
    private javax.swing.JButton btnBuild;
    private javax.swing.JButton btnCline;
    private javax.swing.JButton btnDelivery_notes;
    private javax.swing.JButton btnExchange;
    private javax.swing.JButton btnProduct;
    private javax.swing.JButton btnReturns;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnStaff;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel tblCN;
    // End of variables declaration//GEN-END:variables
}
