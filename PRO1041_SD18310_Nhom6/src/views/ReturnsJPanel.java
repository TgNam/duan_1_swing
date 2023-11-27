package views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entity.Bill;
import model.entity.BillDetail;
import model.entity.ReturnBill;
import service.BillDetailService;
import service.BillService;
import service.imple.BillDetailImple;
import service.imple.BillImple;
import service.imple.ReturnBillDetailImple;
import service.imple.ReturnBillImple;

/**
 *
 * @author thiet
 */
public class ReturnsJPanel extends javax.swing.JPanel {

    private BillService biv = new BillImple();
    private BillDetailService bdts = new BillDetailImple();
    private ReturnBillImple returnBillImple = new ReturnBillImple();
    private ReturnBillDetailImple returnBillDetailImple = new ReturnBillDetailImple();

    private DefaultTableModel dtmB;
    private DefaultTableModel dtmBD;
    private List<BillDetail> listProductReturn = null;
    private JFrame jframe;

    public List<BillDetail> getListProductReturn() {
        return listProductReturn;
    }

    public void setListProductReturn(List<BillDetail> listProductReturn) {
        this.listProductReturn = listProductReturn;
    }
    private double money_Sum;
    private int stt = 0;

    public ReturnsJPanel() {
        initComponents();
        panelFormTraHang.setVisible(true);
        this.loadBill();
    }

    public void loadBill() {
        dtmB = (DefaultTableModel) this.tblBill.getModel();
        dtmB.setRowCount(0);
        stt = 1;
        for (Bill b : this.biv.getAll()) {
            Object[] ob = {
                stt++,
                b.getId(),
                b.getUserId().getFullName(),
                b.getUserId().getNumberPhone(),
                b.getStatus(),
                b.getCreatedAt()
            };
            dtmB.addRow(ob);
        }
    }

    public void loadBillDetail(String id) {
        dtmBD = (DefaultTableModel) this.tblBillDetails.getModel();
        dtmBD.setRowCount(0);
        stt = 1;
        for (BillDetail bdt : this.bdts.getbill_all(id)) {
            Object[] ob = {
                stt++,
                bdt.getProductDetailId().getProductId().getName_product(),
                bdt.getProductDetailId().getColorId().getNameColor(),
                bdt.getProductDetailId().getSizeId().getNameSize(),
                bdt.getQuantityPurchased(),
                bdt.getPriceNow()
            };
            dtmBD.addRow(ob);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFormTraHang = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        btnInPhieuGH = new javax.swing.JButton();
        btnTraHang = new javax.swing.JButton();
        btnDoiHang = new javax.swing.JButton();

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Chi tiết hóa đơn");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LỊCH SỬ HÓA ĐƠN");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Hóa đơn");

        tblBill.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBill.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBillMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);
        if (tblBill.getColumnModel().getColumnCount() > 0) {
            tblBill.getColumnModel().getColumn(0).setMinWidth(50);
            tblBill.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblBillDetails.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Màu", "Size", "Số lượng", "Đơn giá", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBillDetails.setRowHeight(25);
        tblBillDetails.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBillDetails);
        if (tblBillDetails.getColumnModel().getColumnCount() > 0) {
            tblBillDetails.getColumnModel().getColumn(0).setMinWidth(50);
            tblBillDetails.getColumnModel().getColumn(0).setMaxWidth(50);
            tblBillDetails.getColumnModel().getColumn(2).setMinWidth(70);
            tblBillDetails.getColumnModel().getColumn(2).setMaxWidth(70);
            tblBillDetails.getColumnModel().getColumn(3).setMinWidth(70);
            tblBillDetails.getColumnModel().getColumn(3).setMaxWidth(70);
            tblBillDetails.getColumnModel().getColumn(4).setMinWidth(70);
            tblBillDetails.getColumnModel().getColumn(4).setMaxWidth(70);
            tblBillDetails.getColumnModel().getColumn(6).setMinWidth(30);
            tblBillDetails.getColumnModel().getColumn(6).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnInPhieuGH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnInPhieuGH.setText("IN PHIẾU GIAO HÀNG");

        btnTraHang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnTraHang.setText("TRẢ HÀNG");
        btnTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraHangActionPerformed(evt);
            }
        });

        btnDoiHang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnDoiHang.setText("ĐỔI HÀNG");
        btnDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormTraHangLayout = new javax.swing.GroupLayout(panelFormTraHang);
        panelFormTraHang.setLayout(panelFormTraHangLayout);
        panelFormTraHangLayout.setHorizontalGroup(
            panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormTraHangLayout.createSequentialGroup()
                        .addGroup(panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInPhieuGH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelFormTraHangLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
        panelFormTraHangLayout.setVerticalGroup(
            panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormTraHangLayout.createSequentialGroup()
                        .addComponent(btnDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInPhieuGH, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGap(0, 47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFormTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelFormTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
        int row = tblBill.getSelectedRow();
        if (row != -1) {
            Bill bill = biv.getAll().get(row);
            String id = bill.getId();
            loadBillDetail(id);
        }

    }//GEN-LAST:event_tblBillMouseClicked

    private void tblBillMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMousePressed

    }//GEN-LAST:event_tblBillMousePressed

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        int indexBillDetail = tblBillDetails.getSelectedRow();
        int indexBill = tblBill.getSelectedRow();
        Bill bill = biv.getAll().get(indexBill);

        ArrayList<BillDetail> billDetails = bdts.getbill_all(bill.getId());
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void btnTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraHangActionPerformed
        int rowCount = tblBillDetails.getRowCount();
        int index = tblBill.getSelectedRow();
        listProductReturn = new ArrayList<>();
        if (index != -1) {
            // Lấy ra bill đang được chọn
            int indexBill = tblBill.getSelectedRow();
            Bill bill = biv.getAll().get(indexBill);
            // Lấy ra các sản phẩm có trong bill này
            List<BillDetail> billDetails = bdts.getbill_all(bill.getId());
            Boolean isChecked = null;
            // duyệt qua từng dòng với i là đại diện cho index của các dòng
            for (int i = 0; i < rowCount; i++) {
                isChecked = (Boolean) tblBillDetails.getValueAt(i, tblBillDetails.getColumnCount() - 1);
                if (isChecked != null && isChecked) {
                    // nếu dòng này đang được tích thì lấy ra BillDetail tại dòng đó
                    billDetails.get(i).setBillId(bill);
                    listProductReturn.add(billDetails.get(i));
                }
            }
            if (listProductReturn.size() == 0) {
                JOptionPane.showMessageDialog(this, "Chọn sản phẩm muốn trả", "Trả hàng", 0);
                return;
            }

            ReturnsForm returnsForm = new ReturnsForm(jframe, true);
            returnsForm.setBillDetails(listProductReturn);
            returnsForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn muốn trả!", "Trả hàng", 0);
            return;
        }
    }//GEN-LAST:event_btnTraHangActionPerformed

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiHangActionPerformed

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
    private javax.swing.JButton btnDoiHang;
    private javax.swing.JButton btnInPhieuGH;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelFormTraHang;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblBillDetails;
    // End of variables declaration//GEN-END:variables
}
