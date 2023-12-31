/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.entity.Bill;
import model.entity.Color;
import model.entity.Custom;
import model.entity.ExchangeBill;
import model.entity.ExchangeBillDetail;
import model.entity.Material;
import model.entity.ProductDetail;
import model.entity.Size;
import model.entity.Thickness;
import repository.ExchangeRepository;
import repository.Exchange_DetailRepository;
import repository.ProductDetailRepository;
import service.ExchangeService;
import service.Exchange_detailServict;
import service.ProductDetailService;
import service.imple.Exchage_DetailImple;
import service.imple.ExchangeImple;
import service.imple.ProductDetailImple;

/**
 *
 * @author thiet
 */
public class ExchangeJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ExchangeJDialog
     */
    private ProductDetailService pdds;
    private ExchangeService exs;
    private Exchange_detailServict exds;

    ArrayList<ProductDetail> List;

    private DefaultTableModel dtm;
    private DefaultTableModel dtmPR;
    double sum_Money;

    int index;
    String idBill;
    Timestamp created_at;
    double sum;

    public ExchangeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pdds = new ProductDetailImple();
        exs = new ExchangeImple();
        exds = new Exchage_DetailImple();
        List = new ArrayList<>();
        System.out.println(idBill);
        System.out.println(lblMoney_Sum.toString());
        this.loadProduct_Detail();

    }

    public void setMoney(double money) {
        lblMoney_Sum.setText(String.valueOf(money));
        sum_Money = money;
    }

    public void setIDbill(String id) {
        idBill = id;
    }

    public void loadProduct_Detail() {
        dtm = (DefaultTableModel) this.tblProduct_Detail.getModel();
        dtm.setRowCount(0);
        for (ProductDetail pdt : this.pdds.getproduct_Exchange()) {
            Object[] ob = {
                pdt.getId(),
                pdt.getProductId().getName_product(),
                pdt.getProductId().getCustome_id().getNameCustom(),
                pdt.getProductId().getMaterial_id().getNameMaterial(),
                pdt.getProductId().getThickness_id().getGsm(),
                pdt.getColorId().getNameColor(),
                pdt.getSizeId().getNameSize(),
                pdt.getProductId().getProduct_price(),
                pdt.getQuantity()
            };
            dtm.addRow(ob);
        }
    }

    public void loadProduct_Ex() {
        dtmPR = (DefaultTableModel) this.tblProduct_Ex.getModel();
        dtmPR.setRowCount(0);
        for (ProductDetail pdt : this.List) {
            Object[] ob = {
                pdt.getId(),
                pdt.getProductId().getName_product(),
                pdt.getProductId().getCustome_id().getNameCustom(),
                pdt.getProductId().getMaterial_id().getNameMaterial(),
                pdt.getProductId().getThickness_id().getGsm(),
                pdt.getColorId().getNameColor(),
                pdt.getSizeId().getNameSize(),
                pdt.getProductId().getProduct_price(),
                pdt.getQuantity()
            };
            dtmPR.addRow(ob);
        }
    }

    private double calculateTotalSum(DefaultTableModel model) {
        double totalSum = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int quantity = Integer.parseInt(model.getValueAt(i, 8).toString());
            double price = Double.parseDouble(model.getValueAt(i, 7).toString());
            totalSum += quantity * price;
        }
        return totalSum;
    }

    //them cai nay ngay 28//11
    private int findRowInTable_Product_Detail(String idPr) {
        for (int i = 0; i < tblProduct_Detail.getRowCount(); i++) {
            if (idPr.trim().equals(tblProduct_Detail.getValueAt(i, 0).toString().trim())) {
                return i;
            }
        }
        return -1;
    }

    private int findRowInTable_Product_Ex(String idEX) {
        for (int i = 0; i < tblProduct_Ex.getRowCount(); i++) {
            if (tblProduct_Ex.getValueAt(i, 0) == null) {
                return -1;
            } else if (idEX.trim().equals(tblProduct_Ex.getValueAt(i, 0).toString().trim())) {
                return i;
            }
        }
        return -1;

    }

//    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProduct_Detail = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();
        lblMoney_Sum = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProduct_Ex = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaReason = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        tblProduct_Detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name_Product", "Name_Custom", "Name_Meterial", "gsm", "Name_color", "Name_Size", "Product_price", "Quantity"
            }
        ));
        tblProduct_Detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_DetailMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblProduct_Detail);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tổng tiền trả");

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        lblMoney_Sum.setText("0.0 đ");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        tblProduct_Ex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Name_Product", "Name_Custom", "Name_Meterial", "gsm", "Name_color", "Name_Size", "Product_price", "Quantity"
            }
        ));
        tblProduct_Ex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_ExMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProduct_Ex);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        txaReason.setColumns(20);
        txaReason.setRows(5);
        jScrollPane1.setViewportView(txaReason);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblMoney_Sum, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnxoa)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHoanThanh)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblMoney_Sum))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnxoa)
                            .addComponent(btnHoanThanh)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProduct_DetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_DetailMouseClicked
        int row = tblProduct_Detail.getSelectedRow();
        if (row < 0) {
            return;
        } else {
            int quantity_product = Integer.parseInt(tblProduct_Detail.getValueAt(row, 8).toString());
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Moi ban nhap so luong: "));
            if(quantity > Integer.parseInt(tblProduct_Detail.getValueAt(row, 8).toString())){
                JOptionPane.showMessageDialog(this, "Khong duoc nhap qua so luong sp trong kho");
                return;
            }
            
            
            if (quantity > 0) {
                int resultQauntity = quantity_product - quantity;
                dtm.setValueAt(resultQauntity, row, 8);

//                kien tra xem ton tai chua
                String idCheck = tblProduct_Detail.getValueAt(row, 0).toString();
                System.out.println(idCheck);
                int checkEx = this.findRowInTable_Product_Ex(idCheck);
                System.out.println("row" + checkEx);
                
                if (checkEx != -1) {
                    int currentQuantity_Ex = (int) tblProduct_Ex.getValueAt(checkEx, 8);
                    tblProduct_Ex.setValueAt(currentQuantity_Ex + quantity, checkEx, 8);
                    String id = tblProduct_Detail.getValueAt(row, 0).toString().trim();
                    String name_Product = tblProduct_Detail.getValueAt(row, 1).toString().trim();
                    String name_Custom = tblProduct_Detail.getValueAt(row, 2).toString().trim();
                    String name_Material = tblProduct_Detail.getValueAt(row, 3).toString().trim();
                    int gsm = Integer.parseInt(tblProduct_Detail.getValueAt(row, 4).toString());
                    String name_Coler = tblProduct_Detail.getValueAt(row, 5).toString().trim();
                    String name_Size = tblProduct_Detail.getValueAt(row, 6).toString().trim();
                    BigDecimal price = new BigDecimal(tblProduct_Detail.getValueAt(row, 7).toString().trim());

                    Custom custom = new Custom(name_Custom);
                    Material material = new Material(name_Material);
                    Thickness thickness = new Thickness(gsm);
                    Color color = new Color(name_Coler);
                    Size size = new Size(name_Size);

                    model.entity.Product product = new model.entity.Product(price, custom, material, thickness, name_Product);
                    ProductDetail pdt = new ProductDetail(currentQuantity_Ex + quantity , color, id, product, size);
                    this.List.set(checkEx, pdt);
                    this.loadProduct_Ex();
                } else {
                    String id = tblProduct_Detail.getValueAt(row, 0).toString().trim();
                    String name_Product = tblProduct_Detail.getValueAt(row, 1).toString().trim();
                    String name_Custom = tblProduct_Detail.getValueAt(row, 2).toString().trim();
                    String name_Material = tblProduct_Detail.getValueAt(row, 3).toString().trim();
                    int gsm = Integer.parseInt(tblProduct_Detail.getValueAt(row, 4).toString());
                    String name_Coler = tblProduct_Detail.getValueAt(row, 5).toString().trim();
                    String name_Size = tblProduct_Detail.getValueAt(row, 6).toString().trim();
                    BigDecimal price = new BigDecimal(tblProduct_Detail.getValueAt(row, 7).toString().trim());

                    Custom custom = new Custom(name_Custom);
                    Material material = new Material(name_Material);
                    Thickness thickness = new Thickness(gsm);
                    Color color = new Color(name_Coler);
                    Size size = new Size(name_Size);

                    model.entity.Product product = new model.entity.Product(price, custom, material, thickness, name_Product);
                    ProductDetail pdt = new ProductDetail(quantity, color, id, product, size);
                    this.List.add(pdt);
                    this.loadProduct_Ex();
                }
                
                sum = calculateTotalSum(dtmPR);
                lblMoney_Sum.setText(String.valueOf(sum_Money - sum));
            }
        }
    }//GEN-LAST:event_tblProduct_DetailMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        int row = tblProduct_Ex.getSelectedRow();
        int rowprd = tblProduct_Ex.getRowCount();
        int rowpr = tblProduct_Detail.getRowCount();
        int row_Detail = tblProduct_Detail.getSelectedRow();

        if (row < 0) {
            return;
        }
        int quantity_product = Integer.parseInt(tblProduct_Ex.getValueAt(row, 8).toString());
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Moi ban nhap so luong: "));
        if (quantity > 0) {
            int resultQauntity = quantity_product - quantity;
            tblProduct_Ex.setValueAt(String.valueOf(resultQauntity), row, 8);
            //kien tra xem ton tai chua

            int checkPr = this.findRowInTable_Product_Detail(tblProduct_Ex.getValueAt(row, 0).toString().trim());
            if (checkPr != -1) {
                int currentQuantity_Detail = (int) tblProduct_Detail.getValueAt(checkPr, 8);
                tblProduct_Detail.setValueAt(currentQuantity_Detail + quantity, checkPr, 8);
            }

            double sum2 = calculateTotalSum(dtmPR);
            System.out.println("so tien" + sum2);

            lblMoney_Sum.setText(String.valueOf(sum_Money - sum2));

            for (int i = 0; i < rowprd; i++) {
                String id = tblProduct_Ex.getValueAt(i, 0).toString();
                String name_Product = tblProduct_Ex.getValueAt(i, 1).toString().trim();
                String name_Custom = tblProduct_Ex.getValueAt(i, 2).toString().trim();
                String name_Material = tblProduct_Ex.getValueAt(i, 3).toString().trim();
                int gsm = Integer.parseInt(tblProduct_Ex.getValueAt(i, 4).toString());
                String name_Coler = tblProduct_Ex.getValueAt(i, 5).toString().trim();
                String name_Size = tblProduct_Ex.getValueAt(i, 6).toString().trim();
                BigDecimal price = new BigDecimal(tblProduct_Ex.getValueAt(row, 7).toString().trim());
                int sl = Integer.parseInt(tblProduct_Ex.getValueAt(i, 8).toString());

                Custom custom = new Custom(name_Custom);
                Material material = new Material(name_Material);
                Thickness thickness = new Thickness(gsm);
                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                model.entity.Product product = new model.entity.Product(price, custom, material, thickness, name_Product);
                ProductDetail pdt = new ProductDetail(sl, color, id, product, size);

                List.set(i, pdt);

            }

            this.loadProduct_Ex();
        } else {
            return;
        }
    }//GEN-LAST:event_btnxoaActionPerformed


    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        int row = tblProduct_Ex.getRowCount();
        System.out.println("id build " + idBill);
        LocalDateTime today = LocalDateTime.now();
        Timestamp todayEx = new java.sql.Timestamp(today.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        created_at = todayEx;
        ExchangeBill ex = new ExchangeBill(new Bill(idBill), created_at, txaReason.getText().trim());
        System.out.println("id bill: " + ex.getBillId().getId());
        System.out.println("ngay tao: " + ex.getCreatedAt());
        System.out.println("mo ta: " + ex.getDescribeReason());
        if (exs.Insert(ex)) {
            JOptionPane.showMessageDialog(this, "Them 1");
            for (int i = 0; i < row; i++) {
                int sl = Integer.parseInt(tblProduct_Ex.getValueAt(i, 8).toString());
                String idSP = tblProduct_Ex.getValueAt(i, 0).toString();
                ExchangeBillDetail exd = new ExchangeBillDetail(sl, new ExchangeBill(created_at), new ProductDetail(idSP));
                if (this.exds.insert(exd) == true && this.pdds.getQuantity(idSP, sl)) {
                    JOptionPane.showMessageDialog(this, "them 2 thanh cong");
                } else {
                    JOptionPane.showMessageDialog(this, "them 2 that bai");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "that bai");
        }


    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void tblProduct_ExMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_ExMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProduct_ExMouseClicked

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
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExchangeJDialog dialog = new ExchangeJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblMoney_Sum;
    private javax.swing.JTable tblProduct_Detail;
    private javax.swing.JTable tblProduct_Ex;
    private javax.swing.JTextArea txaReason;
    // End of variables declaration//GEN-END:variables
}
