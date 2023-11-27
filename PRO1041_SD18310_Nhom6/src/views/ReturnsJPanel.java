/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entity.Bill;
import model.entity.BillDetail;
import service.BillDetailService;
import service.BillService;
import service.imple.BillDetailImple;
import service.imple.BillImple;

/**
 *
 * @author thiet
 */
public class ReturnsJPanel extends javax.swing.JPanel {

    private BillService biv = new BillImple();
    private BillDetailService bdts = new BillDetailImple();

    private DefaultTableModel dtmB;
    private DefaultTableModel dtmBD;
    private List<BillDetail> listProductReturn = new ArrayList<>();
    private double money_Sum;
    private int stt = 0;

    /**
     * Creates new form Exchange
     */
    public ReturnsJPanel() {
        initComponents();
        panelFormTraHang.setVisible(true);
        panelTraHang.setVisible(false);
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

    public void loadTableProductReturn(List<BillDetail> list) {
        dtmBD = (DefaultTableModel) this.tblProductReturn.getModel();
        dtmBD.setRowCount(0);
        stt = 1;
        for (BillDetail bdt : list) {
            System.out.println(bdt.getProductDetailId().getProductId().getId());
            Object[] ob = {
                stt++,
                bdt.getBillId().getId(),
                bdt.getProductDetailId().getProductId().getName_product(),
                bdt.getQuantityPurchased(),
                bdt.getProductDetailId().getColorId().getNameColor(),
                bdt.getProductDetailId().getSizeId().getNameSize(),
                bdt.getPriceNow()
            };
            dtmBD.addRow(ob);
        }
    }

    public void reset() {
        txtDaTT.setText("");
        txtHoanTra.setText("");
        txtLyDo.setText("");
        txtMaKH.setText("");
        txtTenKH.setText("");
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
        panelTraHang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductReturn = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDaTT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoanTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtLyDo = new javax.swing.JTextArea();
        btnHuy = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Chi tiết hóa đơn");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LỊCH SỬ HÓA ĐƠN");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Hóa đơn");

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

        tblBillDetails.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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

        btnInPhieuGH.setText("IN PHIẾU GIAO HÀNG");

        btnTraHang.setText("TRẢ HÀNG");
        btnTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraHangActionPerformed(evt);
            }
        });

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
                .addGap(0, 12, Short.MAX_VALUE))
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
                .addGap(0, 41, Short.MAX_VALUE))
        );

        tblProductReturn.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblProductReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên sản phẩm", "Số  lượng ", "Màu ", "Size", "Đơn giá", "Nhập số lượng trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductReturn.setRowHeight(25);
        tblProductReturn.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblProductReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductReturnMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductReturn);
        if (tblProductReturn.getColumnModel().getColumnCount() > 0) {
            tblProductReturn.getColumnModel().getColumn(0).setMinWidth(50);
            tblProductReturn.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProductReturn.getColumnModel().getColumn(3).setMinWidth(100);
            tblProductReturn.getColumnModel().getColumn(3).setMaxWidth(100);
            tblProductReturn.getColumnModel().getColumn(4).setMinWidth(100);
            tblProductReturn.getColumnModel().getColumn(4).setMaxWidth(100);
            tblProductReturn.getColumnModel().getColumn(5).setMinWidth(100);
            tblProductReturn.getColumnModel().getColumn(5).setMaxWidth(100);
            tblProductReturn.getColumnModel().getColumn(7).setMinWidth(110);
            tblProductReturn.getColumnModel().getColumn(7).setMaxWidth(110);
        }

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Danh sách sản phẩm muốn trả:");

        txtMaKH.setEditable(false);
        txtMaKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        txtTenKH.setEditable(false);
        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Tên khách hàng: ");

        txtDaTT.setEditable(false);
        txtDaTT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Đã thanh toán: ");
        jLabel5.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                jLabel5AncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });

        txtHoanTra.setEditable(false);
        txtHoanTra.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Hoàn trả:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Lý do: ");

        txtLyDo.setColumns(20);
        txtLyDo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtLyDo.setRows(5);
        jScrollPane4.setViewportView(txtLyDo);

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy.setText("HỦY");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXacNhan.setText("XÁC NHẬN TRẢ HÀNG");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTraHangLayout = new javax.swing.GroupLayout(panelTraHang);
        panelTraHang.setLayout(panelTraHangLayout);
        panelTraHangLayout.setHorizontalGroup(
            panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTraHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTraHangLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTraHangLayout.createSequentialGroup()
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelTraHangLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelTraHangLayout.createSequentialGroup()
                                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDaTT))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(207, 207, 207)))))
                .addContainerGap())
        );
        panelTraHangLayout.setVerticalGroup(
            panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(5, 5, 5)
                        .addComponent(txtHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFormTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFormTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
        int row = tblBill.getSelectedRow();
        if (row != -1) {
            Bill bill = biv.getAll().get(row);
            String id = bill.getId();
            loadBillDetail(id);

            txtTenKH.setText(bill.getUserId().getFullName());
            txtMaKH.setText(bill.getUserId().getId());
            txtDaTT.setText(bill.getTotalCost().toString());
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

    private void jLabel5AncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel5AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5AncestorMoved

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        panelFormTraHang.setVisible(true);
        panelTraHang.setVisible(false);
        listProductReturn = new ArrayList<>();
        tblBillDetails.clearSelection();
        int rowCount = tblBillDetails.getRowCount();
        Boolean isChecked = null;
        reset();

        for (int i = 0; i < rowCount; i++) {
            isChecked = (Boolean) tblBillDetails.getValueAt(i, tblBillDetails.getColumnCount() - 1);
            if (isChecked != null && isChecked) {
                tblBillDetails.setValueAt(false, i, tblBillDetails.getColumnCount() - 1);
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (txtLyDo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do trả hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraHangActionPerformed
        int rowCount = tblBillDetails.getRowCount();
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
        loadTableProductReturn(listProductReturn);
        panelFormTraHang.setVisible(false);
        panelTraHang.setVisible(true);
    }//GEN-LAST:event_btnTraHangActionPerformed

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiHangActionPerformed

    private void tblProductReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductReturnMouseClicked
        int index = tblBill.getSelectedRow();
        int rowCount = tblProductReturn.getRowCount();

        Bill bill = biv.getAll().get(index);
        List<BillDetail> billDetails = bdts.getbill_all(bill.getId());
        Integer soLuongInput = 0;
        Integer soLuongGoc = 0;
        BigDecimal sumMoney = BigDecimal.ZERO;

        for (int i = 0; i < rowCount; i++) {
            if (tblProductReturn.getValueAt(i, 7) != null) {
                try {
                    soLuongInput = Integer.parseInt(tblProductReturn.getValueAt(i, 7).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số", "Lỗi", 2);
                    return;
                }
                soLuongGoc = Integer.parseInt(tblProductReturn.getValueAt(i, 3).toString());
                if (soLuongInput > soLuongGoc) {
                    JOptionPane.showMessageDialog(this, "Số lượng muốn trả không phù hợp", "Lỗi", 2);
                    return;
                }
                BigDecimal productPrice = BigDecimal.valueOf(Double.parseDouble(tblProductReturn.getValueAt(i, 6) + ""));
                BigDecimal productTotal = BigDecimal.valueOf(soLuongInput).multiply(productPrice);
                sumMoney = sumMoney.add(productTotal);
            }
        }
        txtHoanTra.setText(sumMoney.toString());
    }//GEN-LAST:event_tblProductReturnMouseClicked

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
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInPhieuGH;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelFormTraHang;
    private javax.swing.JPanel panelTraHang;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTable tblProductReturn;
    private javax.swing.JTextField txtDaTT;
    private javax.swing.JTextField txtHoanTra;
    private javax.swing.JTextArea txtLyDo;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
