/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import Util.Validate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entity.Bill;
import model.entity.BillDetail;
import model.entity.ProductDetail;
import model.entity.User;
import model.entity.Voucher;
import repository.VoucherResponsitory;
import service.BillDetailService;
import service.BillService;
import service.ProductDetailService;
import service.ProductService;
import service.UserRoleService;
import service.UserService;
import service.imple.BillDetailImple;
import service.imple.BillImple;
import service.imple.ProductDetailImple;
import service.imple.ProductImple;
import service.imple.UserImple;
import service.imple.UserRoleImple;

/**
 *
 * @author thiet
 */
public class BillJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private ProductService productService = new ProductImple();
    private ProductDetailService productDetailService = new ProductDetailImple();
    private BillService billService = new BillImple();
    private BillDetailService billDetailService = new BillDetailImple();
    private UserService userService = new UserImple();
    private UserRoleService userRoleService = new UserRoleImple();
    private VoucherResponsitory voucherResponsitory = new VoucherResponsitory();
    private Validate vl = new Validate();
    private Date nowDate = null;
    private boolean checkKH = false;

    /**
     * Creates new form Exchange
     */
    public BillJPanel() {
        initComponents();
        datarowProcuct();
        datarowBill();
        resetProduct();
        jLKH.setVisible(false);
        txtUser.setVisible(false);
        resetformbill();
    }
    public void resetformbill(){
        jLtotal_cost.setText( "0.0 ");
        jLinto_money.setText("0.0 ");
        jLGiamGia.setText("0.0 ");
    }
    //lấy thời gian hiện tại
    public static Date getCurrentDateTime() {
        try {
            // Lấy thời gian hiện tại
            Date currentDate = new Date();

            // Định dạng ngày tháng năm giờ phút giây
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Chuyển đổi thành chuỗi theo định dạng
            String formattedDateTime = dateFormat.format(currentDate);

            // Chuyển lại thành đối tượng Date
            Date date = dateFormat.parse(formattedDateTime);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //đổ đữ liệu cho bảng sản phẩm
    public void datarowProcuct() {
        tableModel = (DefaultTableModel) tblProduct.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (model.entity.Product product : productService.getList_sale()) {
            tableModel.addRow(new Object[]{
                index++,
                product.getId(),
                product.getName_product(),
                product.getProduct_price() + " VND",
                product.getSale_id().getSale() + " %",
                product.checkTrangThai()});
        }
    }

    //đổ đữ liệu cho bảng sản phẩm chi tiết
    public void datarowProcuctDetail(String idProduct) {
        tableModel = (DefaultTableModel) tblProductDetail.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (ProductDetail productDetail : productDetailService.get_ProductDetails_id_Bill(idProduct,"1")) {
            tableModel.addRow(new Object[]{
                index++,
                productDetail.getProductId().getName_product(),
                productDetail.getColorId().getNameColor(),
                productDetail.getSizeId().getNameSize(),
                productDetail.getQuantity(),
                productDetail.checkTrangThai()});
        }
    }
    //đổ đữ liệu cho bảng bill

    public void datarowBill() {
        tableModel = (DefaultTableModel) tblbill.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (Bill bill : billService.getListBill_0()) {
            tableModel.addRow(new Object[]{
                index++,
                bill.getId(),
                bill.getUserId().getFullName(),
                bill.getUserId().getNumberPhone(),
                bill.checkTrangThai(),
                bill.getCreatedAt()
            });
        }
    }

    //đổ đữ liệu cho bảng giỏ hàng
    public void datarowShoppingCart(String id) {
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        // Khởi tạo biến để lưu tổng giá tiền
        BigDecimal totalAmount = BigDecimal.ZERO;
        
        for (BillDetail billDetail : billDetailService.getBill_idBill(id)) {
            // Lấy giá sản phẩm từ đối tượng billDetail
            BigDecimal Product_price = billDetail.getProductDetailId().getProductId().getProduct_price();
            // Thiết lập giá trị khuyến mãi ban đầu là 0
            BigDecimal Sale_Product_price = BigDecimal.ZERO;
            // Kiểm tra xem có sự giảm giá được định nghĩa trong đối tượng sản phẩm và có giá trị lớn hơn hoặc bằng 0 không
            if (billDetail.getProductDetailId().getProductId().getSale_id() != null && billDetail.getProductDetailId().getProductId().getSale_id().getSale() >= 0.0) {
                // Nếu có khuyến mãi, gán giá trị giảm giá cho biến Sale_Product_price
                Sale_Product_price = BigDecimal.valueOf(billDetail.getProductDetailId().getProductId().getSale_id().getSale());
            }
            BigDecimal unitPrice = Product_price.subtract(Product_price.multiply(Sale_Product_price.divide(BigDecimal.valueOf(100.0))));
            //tính số tiền của sản phẩm chi tiết đó đã mua
            BigDecimal totalPriceForItem = unitPrice.multiply(BigDecimal.valueOf(Double.valueOf(billDetail.getQuantityPurchased())));
            tableModel.addRow(new Object[]{
                index++,
                billDetail.getProductDetailId().getProductId().getName_product(),
                billDetail.getProductDetailId().getColorId().getNameColor(),
                billDetail.getProductDetailId().getSizeId().getNameSize(),
                billDetail.getQuantityPurchased(),
                unitPrice.setScale(2, RoundingMode.HALF_UP) + " VND"
            });
            // Cộng giá tiền của mỗi sản phẩm vào tổng
            totalAmount = totalAmount.add(totalPriceForItem);
        }
        resetformbill();
        jLtotal_cost.setText(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
        jLinto_money.setText(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
        
    }

    //thêm sản phẩm lên form SP
    public void filltableSP(model.entity.Product product, int index) {
        ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(),"1").get(index);
        jLSTT.setText(String.valueOf(index + 1));
        jLname.setText(pd.getProductId().getName_product());
        jLColor.setText(pd.getColorId().getNameColor());
        jLSize.setText(pd.getSizeId().getNameSize());
    }

    public void resetProduct() {
        jLSTT.setText("");
        jLname.setText("");
        jLColor.setText("");
        jLSize.setText("");
        txtsoluong.setText("");
        tableModel = (DefaultTableModel) tblProductDetail.getModel();
        tableModel.setRowCount(0);
    }

    //Kiểm tra số điện thoại
    public Boolean checkphone() {
        try {
            String PhoneNumber = txtPhoneNumber.getText();
            nowDate = getCurrentDateTime();
            if (PhoneNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống!");
                return false;
            } else if (!vl.isCheckPhone(PhoneNumber)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điện thoại cần 10 kí tự và bắt đầu bằng số 0");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return false;
        }
    }

    //Kiểm tra tên khách hàng
    public Boolean checkKH() {
        try {
            String nameKH = txtUser.getText();
            nowDate = getCurrentDateTime();
            if (nameKH.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên KH!");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {  
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return false;
        }
    }

    //Kiểm tra số lượng
    public Boolean checkSl() {
        //Lấy vị trí click của sản phẩm chi tiết
        int indexProductDetail = tblProductDetail.getSelectedRow();
        //Lấy vị trí click của sản phẩm
        int indexProduct = tblProduct.getSelectedRow();
        if (indexProduct >= 0) {
            if (indexProductDetail >= 0) {
                try {
                    String SL = txtsoluong.getText();
                    nowDate = getCurrentDateTime();
                    //lấy dữ liệu của đối tượng sản phẩm tại vị trí indexProduct
                    model.entity.Product product = productService.getList_sale().get(indexProduct);
                    ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(),"1").get(indexProductDetail);
                    if (SL.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng không được để trống số lượng!");
                        return false;
                    } else if (Integer.valueOf(SL) > Integer.valueOf(pd.getQuantity())) {
                        JOptionPane.showMessageDialog(this, "Số lượng bạn mua vượt quá" + "\n" + "số lượng sản phẩm còn lại trong kho");
                        return false;
                    } else if (Integer.valueOf(SL) < Integer.valueOf("0")) {
                        JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                        return false;
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm chi tiết");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm");
            return false;
        }
    }

    //Kiểm tra id voucher
    public Boolean checkVoucher() {
        String nameVoucher = txtVoucher.getText();
        try {
//            
            if (nameVoucher.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên Voucher!");
                return false;
            }  if (!vl.isCheckVoucher(nameVoucher)) {
                JOptionPane.showMessageDialog(this, "Định dạng của voucher không đúng");
                return false;
            } 
//            else if (voucher == null) {
//                JOptionPane.showMessageDialog(this, "Voucher không tồn tại!");
//                return false;
//            }
            else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại voucher!");
            return false;

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblbill = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblShoppingCart = new javax.swing.JTable();
        bthdeleteproduct = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLKH = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        tblKTvoucher = new javax.swing.JButton();
        bthresetvoucher = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLtotal_cost = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLGiamGia = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLinto_money = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        bthpayment = new javax.swing.JButton();
        bthaddbill = new javax.swing.JButton();
        bthdeleteBill = new javax.swing.JButton();
        bthresetShoppingCart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductDetail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bthADDProduct_GioHang = new javax.swing.JButton();
        BthResetProduct = new javax.swing.JButton();
        jLSTT = new javax.swing.JLabel();
        jLname = new javax.swing.JLabel();
        jLColor = new javax.swing.JLabel();
        jLSize = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblbill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Tên KH", "SDT", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbillMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblbill);
        if (tblbill.getColumnModel().getColumnCount() > 0) {
            tblbill.getColumnModel().getColumn(0).setMinWidth(40);
            tblbill.getColumnModel().getColumn(0).setMaxWidth(40);
            tblbill.getColumnModel().getColumn(1).setMinWidth(50);
            tblbill.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Danh Sách hóa đơn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Giỏ hàng");

        tblShoppingCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP ", "Color", "Size", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblShoppingCart);
        if (tblShoppingCart.getColumnModel().getColumnCount() > 0) {
            tblShoppingCart.getColumnModel().getColumn(0).setMinWidth(40);
            tblShoppingCart.getColumnModel().getColumn(0).setMaxWidth(40);
            tblShoppingCart.getColumnModel().getColumn(2).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(2).setMaxWidth(70);
            tblShoppingCart.getColumnModel().getColumn(3).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(3).setMaxWidth(70);
            tblShoppingCart.getColumnModel().getColumn(4).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        bthdeleteproduct.setText("Xóa SP");
        bthdeleteproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthdeleteproductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bthdeleteproduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bthdeleteproduct)
                .addGap(13, 13, 13))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLKH.setText("Tên KH:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("SDT:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLKH)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLKH))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel11.setText("Tạo hóa đơn");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Mã Voucher:");

        tblKTvoucher.setText("Kiểm Tra Voucher");
        tblKTvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblKTvoucherActionPerformed(evt);
            }
        });

        bthresetvoucher.setText("Làm mới Voucher");
        bthresetvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthresetvoucherActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Tổng tiền hàng:");

        jLtotal_cost.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLtotal_cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLtotal_cost.setText("360000");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("VND");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLtotal_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLtotal_cost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel12)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("% Giảm giá:");

        jLGiamGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLGiamGia.setText("3");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("%");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel19)
                .addGap(42, 42, 42))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLGiamGia)
                    .addComponent(jLabel19))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Thành Tiền:");

        jLinto_money.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLinto_money.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLinto_money.setText("349,200");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("VND");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLinto_money, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLinto_money)
                    .addComponent(jLabel18))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(tblKTvoucher)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bthresetvoucher)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblKTvoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(bthresetvoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        bthpayment.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        bthpayment.setText("Thanh Toán");
        bthpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthpaymentActionPerformed(evt);
            }
        });

        bthaddbill.setText("Tạo hóa đơn");
        bthaddbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthaddbillActionPerformed(evt);
            }
        });

        bthdeleteBill.setText("Xóa");
        bthdeleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthdeleteBillActionPerformed(evt);
            }
        });

        bthresetShoppingCart.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        bthresetShoppingCart.setText("Làm mới form");
        bthresetShoppingCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthresetShoppingCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bthpayment, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(bthaddbill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bthdeleteBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bthresetShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bthpayment, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(bthresetShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bthaddbill, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(bthdeleteBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Giỏ Hàng", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Giá", "% Khuyến Mãi", "Status"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(0).setMinWidth(30);
            tblProduct.getColumnModel().getColumn(0).setMaxWidth(40);
            tblProduct.getColumnModel().getColumn(1).setMinWidth(60);
            tblProduct.getColumnModel().getColumn(1).setMaxWidth(100);
            tblProduct.getColumnModel().getColumn(2).setMinWidth(120);
            tblProduct.getColumnModel().getColumn(2).setMaxWidth(200);
            tblProduct.getColumnModel().getColumn(3).setMinWidth(100);
            tblProduct.getColumnModel().getColumn(3).setMaxWidth(150);
            tblProduct.getColumnModel().getColumn(4).setMinWidth(30);
            tblProduct.getColumnModel().getColumn(4).setMaxWidth(70);
            tblProduct.getColumnModel().getColumn(5).setMinWidth(50);
            tblProduct.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Danh sách sản phẩm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblProductDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Color", "Size", "Số lượng", "Trạng thái"
            }
        ));
        tblProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductDetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductDetail);
        if (tblProductDetail.getColumnModel().getColumnCount() > 0) {
            tblProductDetail.getColumnModel().getColumn(0).setMinWidth(20);
            tblProductDetail.getColumnModel().getColumn(0).setMaxWidth(40);
            tblProductDetail.getColumnModel().getColumn(1).setMinWidth(200);
            tblProductDetail.getColumnModel().getColumn(1).setMaxWidth(230);
            tblProductDetail.getColumnModel().getColumn(2).setMinWidth(70);
            tblProductDetail.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Chi tiết sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Thêm sản phẩm vào giỏ hàng");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("STT:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Tên SP:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Color:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Size:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Số lượng:");

        bthADDProduct_GioHang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bthADDProduct_GioHang.setText("Thêm sản phẩm");
        bthADDProduct_GioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthADDProduct_GioHangActionPerformed(evt);
            }
        });

        BthResetProduct.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BthResetProduct.setText("Xóa Form");
        BthResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BthResetProductActionPerformed(evt);
            }
        });

        jLSTT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLSTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSTT.setText("1");

        jLname.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLname.setText("Levents");

        jLColor.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLColor.setText("Trắng");

        jLSize.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSize.setText("Size 1 ");

        txtsoluong.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtsoluong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsoluong.setText("5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BthResetProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bthADDProduct_GioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLSTT))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLname))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLColor))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLSize))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(bthADDProduct_GioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(BthResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chọn Sản Phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductDetailMouseClicked
        int indexProduct = tblProduct.getSelectedRow();
        model.entity.Product product = productService.getList_sale().get(indexProduct);
        int indexProductDetail = tblProductDetail.getSelectedRow();
        filltableSP(product, indexProductDetail);
    }//GEN-LAST:event_tblProductDetailMouseClicked

    private void BthResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BthResetProductActionPerformed
        datarowProcuct();
        resetProduct();
    }//GEN-LAST:event_BthResetProductActionPerformed

    private void bthADDProduct_GioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthADDProduct_GioHangActionPerformed
        //Lấy vị trí click của bill
        int indexBill = tblbill.getSelectedRow();
        //Lấy vị trí click của sản phẩm chi tiết
        int indexProductDetail = tblProductDetail.getSelectedRow();
        //Lấy vị trí click của sản phẩm
        int indexProduct = tblProduct.getSelectedRow();
        try {
            //kiểm tra vị trí của bill
            if (indexBill >= 0) {
                //kiểm tra vị trí của sản phẩm
                if (indexProduct >= 0) {
                    //kiểm tra vị trí của sản phẩm chi tiết
                    if (indexProductDetail >= 0) {
                        if (checkSl()) {
                            //lấy dữ liệu của đối tượng sản phẩm tại vị trí indexProduct
                            model.entity.Product product = productService.getList_sale().get(indexProduct);
                            //kiểm tra sự tồn tại của sản phẩm 
                            if (product != null) {
                                //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexProductDetail
                                ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(),"1").get(indexProductDetail);
                                //kiểm tra sự tồn tại của sản phẩm chi tiết 
                                if (pd != null) {
                                    //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexBill
                                    Bill bill = billService.getListBill_0().get(indexBill);
                                    // lấy thời gian hiện tại 
                                    nowDate = getCurrentDateTime();
                                    //kiểm tra sự tồn tại của bill
                                    if (bill != null) {
                                        //Lấy id của Bill
                                        String idBill = bill.getId();
                                        //Lấy id của ProductDetail
                                        String idProductDetail = pd.getId();
                                        //Lấy id của BillDetail
                                        String idBillDetail = null;
                                        //kiểm tra đối tượng ProductDetail đã tồn tại hay chưa 
                                        boolean checkProductDetail = false;
                                        //tạo biến chứa giá trị của số lương của sản phẩm người dùng nhập
                                        int quantity_product_detail_txt = Integer.parseInt(txtsoluong.getText());
                                        //tạo biến chứa giá trị của số lương của sản phẩm trong billDetail
                                        int quantity_product_billDetail = 0;
                                        //tạo biến chứa giá trị của số lương của sản phẩm trong product_detail
                                        int quantity_product_detail = pd.getQuantity();
                                        //vòng lậy để so sánh id của ProductDetail có tồn tại trong BillDetail hay không 
                                        for (BillDetail billDetail : billDetailService.getBill_idBill(idBill)) {
                                            //kiểm tra xem đối tượng đã tồn tại hay chưa 
                                            if (billDetail.getProductDetailId().getId().equals(idProductDetail)) {
                                                //lấy id của billDetail đó 
                                                idBillDetail = billDetail.getId();
                                                //lấy dữ liệu của sản phẩm trong billdetail
                                                quantity_product_billDetail = Integer.parseInt(billDetail.getQuantityPurchased());
                                                //Đối tượng đã tồn tại chuyển trạng thái sang true
                                                checkProductDetail = true;
                                            }
                                        }
                                        //Khi tối tượng đã tồn tại rồi 
                                        if (checkProductDetail) {
                                            //biến chứa giá trị tổng khi thêm số lượng vào giỏ hàng 
                                            int all_quantity_product_billDetail = quantity_product_detail_txt + quantity_product_billDetail;
                                            //Kiểm tra xem số lượng tổng của sản phẩm nhập + vs sản phẩm trong giỏ hàng có lơn hơn số lượng trong kho hay không
                                            if (all_quantity_product_billDetail > quantity_product_detail) {
                                                JOptionPane.showMessageDialog(this, "Số lượng hàng trong giỏ vượt quá số lượng hàng còn lại trong kho!");
                                            } else {
                                                //kiểm tra xem đã cập nhật lại số lượng hay chưa
                                                boolean checkUpdateProductDetail = billDetailService.Update_bill_datail(all_quantity_product_billDetail, idBillDetail);
                                                if (checkUpdateProductDetail) {
                                                    JOptionPane.showMessageDialog(this, "Đã cập số lượng số lượng hàng trong giỏ hàng thành công!");
                                                } else {
                                                    JOptionPane.showMessageDialog(this, "Lỗi cập số lượng số lượng hàng trong giỏ hàng !");
                                                }
                                            }
                                        }
                                        //Khi tối tượng chưa tồn tại thì sẽ tạo ra 1 đối tượng billDetail mới  
                                        if (!checkProductDetail) {
                                            //kiểm tra xem đã thêm thành công chưa
                                            boolean checkAddProductDetail = billDetailService.add_bill_datail(
                                                    new BillDetail(nowDate, nowDate, txtsoluong.getText()),
                                                    idBill,
                                                    idProductDetail);
                                            if (checkAddProductDetail) {
                                                JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng thành công!");
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng thất bại!");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Lỗi dữ liệu hóa đơn!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Lỗi dữ liệu sản phẩm chi tiết!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu sản phẩm!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm chi tiết");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
        }
        resetProduct();
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthADDProduct_GioHangActionPerformed

    private void tblbillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbillMouseClicked
        //hiển thị sản phẩ trong giỏ hàng 
        int index = tblbill.getSelectedRow();
        Bill b = billService.getListBill_0().get(index);
        datarowShoppingCart(b.getId());
    }//GEN-LAST:event_tblbillMouseClicked

    private void bthaddbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthaddbillActionPerformed
        try {
                    if (checkphone() == true) {
            //tạo ra đối tượng user
            User u = null;
            //kiểm tra số điện thoại
            boolean checkName_phone = false;
            for (User user : userService.getUser_name_phone()) {
                if (user.getNumberPhone().equals(txtPhoneNumber.getText())) {
                    // Nếu số điện thoại của người dùng trong danh sách khớp với số điện thoại nhập vào
                    // Gán người dùng tương ứng vào biến u và đặt cờ kiểm tra checkName_phone thành true
                    u = user;
                    checkName_phone = true;
                }
            }
            //số điện thoại nếu tồn tại 
            if (checkName_phone == true) {
                // Hiển thị và cấu hình các thành phần giao diện đồng thời
                jLKH.setVisible(true); // Hiển thị nhãn chứa thông tin khách hàng
                txtUser.setVisible(true); // Hiển thị ô văn bản để hiển thị thông tin tên khách hàng
                txtUser.setText(u.getFullName()); // Đặt văn bản trong ô là tên đầy đủ của khách hàng
                txtUser.setEditable(false); // Vô hiệu hóa khả năng chỉnh sửa ô văn bản với thông tin khách hàng
                // Vô hiệu hóa khả năng chỉnh sửa ô văn bản cho số điện thoại và hiển thị thông báo
                txtPhoneNumber.setEditable(false); // Vô hiệu hóa khả năng chỉnh sửa ô văn bản cho số điện thoại
                JOptionPane.showMessageDialog(this, "Số điện thoại của khách hàng: " + u.getFullName()); // Hiển thị hộp thoại thông báo với thông tin số điện thoại của khách hàng
                // Lấy ngày và giờ hiện tại
                nowDate = getCurrentDateTime();// Gán giá trị ngày và giờ hiện tại vào biến nowDate
                //tạo hóa đơn mới 
                JOptionPane.showMessageDialog(this, billService.add_bill(new Bill(nowDate, nowDate, nowDate, u)));
            }
            if (checkName_phone == false) {
                // Hiển thị và kích hoạt tính năng chỉnh sửa trên các thành phần giao diện
                jLKH.setVisible(true); // Hiển thị nhãn chứa thông tin khách hàng
                txtUser.setVisible(true); // Hiển thị ô văn bản để hiển thị thông tin tên khách hàng
                txtUser.setEditable(true); // Kích hoạt khả năng chỉnh sửa cho ô văn bản với thông tin tên khách hàng
                // Lấy ngày và giờ hiện tại
                nowDate = getCurrentDateTime(); // Gán giá trị ngày và giờ hiện tại vào biến nowDate   
            }
            if (checkKH == true) {
                if (checkKH == true) {
                    u = new User(nowDate, nowDate, txtUser.getText(), txtPhoneNumber.getText());
                    //thêm user
                    userService.add_user(u);
                    //thêm role_user
                    userRoleService.add_user_role(u, String.valueOf("3"));
                    //thông báo kết quả
                    JOptionPane.showMessageDialog(this, billService.add_bill(new Bill(nowDate, nowDate, nowDate, new User(txtUser.getText(), txtPhoneNumber.getText()))));
                }
                checkKH = false;
            }

        }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        checkKH = true;
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthaddbillActionPerformed

    private void bthresetShoppingCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthresetShoppingCartActionPerformed
        txtUser.setText("");
        txtPhoneNumber.setText("");
        jLKH.setVisible(false);
        txtUser.setVisible(false);
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthresetShoppingCartActionPerformed

    private void bthdeleteproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthdeleteproductActionPerformed
        int indexBillDetail = tblShoppingCart.getSelectedRow();
        int indexBill = tblbill.getSelectedRow();
        try {
            if (indexBill >= 0) {
                if (indexBillDetail >= 0) {
                    Bill bill = billService.getListBill_0().get(indexBill);
                    if (bill != null) {
                        BillDetail billDetail = billDetailService.getBill_idBill(bill.getId()).get(indexBillDetail);
                        if (billDetail != null) {
                            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
                            if (hoi != JOptionPane.YES_NO_OPTION) {
                                JOptionPane.showMessageDialog(this, "Bạn đã không xóa sản phẩm trong giỏ hàng ");
                                return;
                            }
                            boolean check = billDetailService.delete_bill_datail_ShoppingCart(billDetail);
                            if (check) {
                                JOptionPane.showMessageDialog(this, "Đã Xóa sản phẩm");
                            } else {
                                JOptionPane.showMessageDialog(this, "=Xóa sản phẩm bị lỗi");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng trong giỏ hàng!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng Bill!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        resetProduct();
        resetformbill();
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthdeleteproductActionPerformed

    private void tblKTvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblKTvoucherActionPerformed
        try {
            int indexBill = tblbill.getSelectedRow();
            if (indexBill >= 0) {
                String idVoucher = txtVoucher.getText();
                BigDecimal total_cost = BigDecimal.valueOf(Double.parseDouble(jLinto_money.getText()));//200000
                BigDecimal into_money = BigDecimal.ZERO;
                boolean checkVoucher = false;
                BigDecimal sale = BigDecimal.ZERO;
                if (checkVoucher()) {
                    for (Voucher voucher : voucherResponsitory.getAllById(txtVoucher.getText())) {
                        if (voucher.getId().equals(idVoucher)) {
                            sale = BigDecimal.valueOf(voucher.getSaleOf());
                            checkVoucher = true;
                        }
                    }
                    if (checkVoucher) {
                        jLGiamGia.setText(String.valueOf(sale));
                        into_money = total_cost.subtract(total_cost.multiply(sale.divide(BigDecimal.valueOf(100.0))));
                        jLinto_money.setText(String.valueOf(into_money));
                    } else {
                        JOptionPane.showMessageDialog(this, "Voucher không tồn tại!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
    }//GEN-LAST:event_tblKTvoucherActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        int index = tblProduct.getSelectedRow();
        model.entity.Product product = productService.getList_sale().get(index);
        datarowProcuctDetail(product.getId());
    }//GEN-LAST:event_tblProductMouseClicked

    private void bthdeleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthdeleteBillActionPerformed
        int indexBill = tblbill.getSelectedRow();
        try {
            if (indexBill >= 0) {
                Bill bill = billService.getListBill_0().get(indexBill);
                if (bill != null) {
                    int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
                    if (hoi != JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn đã không xóa ");
                        return;
                    }
                    boolean checkBillDetail = billDetailService.delete_bill_datail(bill);
                    boolean checkBill = billService.delete_bill_id(bill);
                    if (checkBillDetail && checkBill) {
                        JOptionPane.showMessageDialog(this, "Đã Xóa Bill");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa Bill bị lỗi");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng Bill!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        resetProduct();
        resetformbill();
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthdeleteBillActionPerformed

    private void bthresetvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthresetvoucherActionPerformed
        jLGiamGia.setText("0.0");
        txtVoucher.setText("");
        jLinto_money.setText(jLtotal_cost.getText());
    }//GEN-LAST:event_bthresetvoucherActionPerformed

    private void bthpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthpaymentActionPerformed
        
    }//GEN-LAST:event_bthpaymentActionPerformed
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
    private javax.swing.JButton BthResetProduct;
    private javax.swing.JButton bthADDProduct_GioHang;
    private javax.swing.JButton bthaddbill;
    private javax.swing.JButton bthdeleteBill;
    private javax.swing.JButton bthdeleteproduct;
    private javax.swing.JButton bthpayment;
    private javax.swing.JButton bthresetShoppingCart;
    private javax.swing.JButton bthresetvoucher;
    private javax.swing.JLabel jLColor;
    private javax.swing.JLabel jLGiamGia;
    private javax.swing.JLabel jLKH;
    private javax.swing.JLabel jLSTT;
    private javax.swing.JLabel jLSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLinto_money;
    private javax.swing.JLabel jLname;
    private javax.swing.JLabel jLtotal_cost;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton tblKTvoucher;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProductDetail;
    private javax.swing.JTable tblShoppingCart;
    private javax.swing.JTable tblbill;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtVoucher;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
