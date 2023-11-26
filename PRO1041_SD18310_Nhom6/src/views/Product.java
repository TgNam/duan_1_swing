/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entity.Color;
import model.entity.Custom;
import model.entity.Material;
import model.entity.ProductDetail;
import model.entity.Size;
import model.entity.Thickness;
import service.ColorService;
import service.CustomService;
import service.MaterialServict;
import service.ProductDetailService;
import service.ProductService;
import service.SizeSevice;
import service.ThicknessService;
import service.imple.ColorImple;
import service.imple.CustomImple;
import service.imple.MaterialImple;
import service.imple.ProductDetailImple;
import service.imple.ProductImple;
import service.imple.SizeImple;
import service.imple.ThicknessImple;

/**
 *
 * @author thiet
 */
public class Product extends javax.swing.JPanel {

    private ProductService pds;
    private ProductDetailService pdds;
    private MaterialServict mts;
    private ThicknessService tns;
    private CustomService cts;
    private SizeSevice ss;
    private ColorService cls = new ColorImple();

    JFrame frame;
    DefaultComboBoxModel dcm;

    int minProduct_tab1 = 1;
    int maxProduct_tab1 = 10;
    int minProduct_tab2 = 1;
    int maxProduct_tab2 = 10;
    int minProduct_detail = 1;
    int maxProduct_detail = 10;
    String idSP;
    String idSPCT;
    String idTT;

    /**
     * Creates new form Product
     */
    public Product(JFrame jFrame) {
        initComponents();
        frame = jFrame;
        pds = new ProductImple();
        pdds = new ProductDetailImple();
        mts = new MaterialImple();
        tns = new ThicknessImple();
        cts = new CustomImple();
        ss = new SizeImple();
        cls = new ColorImple();

        this.loadSPNext();
        this.loadCT();
        this.loadSPPhu();

        this.loadSize();

        this.loadcbbkieu();
        this.loadcbbVL();
        this.loadcbbDD();
        this.loadCBBS();
        this.loadCBBCL();
        this.loadCBBSP();
    }

    public void loadSPNext() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblBang.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (model.entity.Product sp : this.pds.getNext(minProduct_tab1, maxProduct_tab1)) {
            Object[] ob = {
                sp.getId(),
                sp.getName_product(),
                sp.getCreated_at(),
                sp.getUpdated_at(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm",
                sp.getDescription()
            };
            dtm.addRow(ob);
        }
    }

    public void loadSPPhu() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSP.getModel();
        dtm.setRowCount(0);

        for (model.entity.Product sp : this.pds.getNext(minProduct_tab2, maxProduct_tab2)) {
            Object[] ob = {
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm",};
            dtm.addRow(ob);
        }
    }

    public void loadCT() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblBangChiTiet.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (ProductDetail spct : this.pdds.getProductDetails_id(idSP)) {

            Object[] ob = {
                spct.getId(),
                spct.getSizeId().getNameSize(),
                spct.getColorId().getNameColor(),
                spct.getCreatedAt(),
                spct.getUpdatedAt(),
                spct.getQuantity()
            };
            dtm.addRow(ob);
        }
    }

    public void loadSize() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblThuocTinh.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Size d : this.ss.getAll()) {
            Object[] ob = {
                d.getId(),
                d.getNameSize(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadMau() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblThuocTinh.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (Color d : this.cls.getAll()) {
            Object[] ob = {
                d.getId(),
                d.getNameColor(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadVatLieu() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblThuocTinh.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Material d : this.mts.getAll()) {
            Object[] ob = {
                d.getId(),
                d.getNameMaterial(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadDoDay() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblThuocTinh.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Thickness d : this.tns.getAll()) {
            Object[] ob = {
                d.getId(),
                d.getGsm(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadKieu() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblThuocTinh.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Custom d : this.cts.getAll()) {
            Object[] ob = {
                d.getId(),
                d.getNameCustom(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadcbbkieu() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Custom ct : this.cts.getAll()) {
            dcm.addElement(ct.getNameCustom());
        }
        cbbKieu.setModel(dcm);
    }

    public void loadcbbVL() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Material nl : this.mts.getAll()) {
            dcm.addElement(nl.getNameMaterial());
        }
        cbbVatLieu.setModel(dcm);
    }

    public void loadcbbDD() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Thickness dd : this.tns.getAll()) {
            String doDay = String.valueOf(dd.getGsm());
            dcm.addElement(doDay);
        }
        cbbDoDay.setModel(dcm);
    }

    public void loadCBBS() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Size s : this.ss.getAll()) {
            String ten = String.valueOf(s.getNameSize());
            dcm.addElement(ten);
        }
        cbbSize.setModel(dcm);
    }

    public void loadCBBCL() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Color m : this.cls.getAll()) {
            String ten = String.valueOf(m.getNameColor());
            dcm.addElement(ten);
        }
        cbbMau.setModel(dcm);
    }

    public void loadCBBSP() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (model.entity.Product pr : this.pds.getProduct_sell()) {
            String ten = String.valueOf(pr.getName_product());
            dcm.addElement(ten);
        }
        cbbSp.setModel(dcm);
    }

    public void clear() {
        txtName_Product.setText("");
        cbbVatLieu.setSelectedIndex(0);
        txtGia.setText("");
        cbbDoDay.setSelectedIndex(0);
        cbbKieu.setSelectedIndex(0);
        txtMoTa.setText("");
        cbbSp.setSelectedIndex(0);
        cbbMau.setSelectedIndex(0);
        cbbSize.setSelectedIndex(0);
        txtSoLuong.setText("");
        txtName_Attribute.setText("");
        rdoColor.setSelected(true);
        this.loadSize();
    }

    public ProductDetail formct() {
        String ten = cbbSp.getSelectedItem().toString();
        String size = cbbSize.getSelectedItem().toString().trim();
        String mau = cbbMau.getSelectedItem().toString().trim();
        String sl = txtSoLuong.getText().trim();
        if (ten.equals("")) {
            JOptionPane.showMessageDialog(this, "chua co san pham de them");
            return null;
        }
        try {
            int soLuong = Integer.parseInt(sl);
            ProductDetail spct = new ProductDetail(soLuong, new Color(mau), new model.entity.Product(ten), new Size(size));
            return spct;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public model.entity.Product form() {
        String ten = txtName_Product.getText().trim();
        String vatLieu = cbbVatLieu.getSelectedItem().toString();
        String gia = txtGia.getText().trim();
        String doDay = cbbDoDay.getSelectedItem().toString();
        String kieuDang = cbbKieu.getSelectedItem().toString();
        String moTa = txtMoTa.getText().trim();
        if (ten.equals("") || gia.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap du tt");
            return null;
        }
        if (ten.length() > 50) {
            JOptionPane.showMessageDialog(this, "teen ko ddc qua 50 ky tu.");
            return null;
        }
        try {
            double giaTien = Double.parseDouble(gia);
            BigDecimal tien = new BigDecimal(gia);
            if (giaTien < 0) {
                JOptionPane.showMessageDialog(this, "gia tien phai lon hon 0");
                return null;
            }
            int doDaySo = Integer.parseInt(doDay);
            model.entity.Product sp = new model.entity.Product(tien, new Custom(kieuDang), new Material(vatLieu), new Thickness(doDaySo), moTa, ten);
            return sp;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "day ko phai gia tien.");
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkSP(String ten) {
        for (model.entity.Product sp : this.pds.getProduct_sell()) {
            if (ten.equalsIgnoreCase(sp.getName_product())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai.");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPKOSD(String ten) {
        for (model.entity.Product sp : this.pds.getProduct_Stop_selling()) {
            if (ten.equalsIgnoreCase(sp.getName_product())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPCTNB(String ten, String size, String mau) {
        for (ProductDetail sp : this.pdds.getProduct_Detail_Stop_selling()) {
            if (ten.equalsIgnoreCase(sp.getProductId().getName_product()) && size.equalsIgnoreCase(sp.getSizeId().getNameSize()) && mau.equalsIgnoreCase(sp.getColorId().getNameColor())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPCTCB(String ten, String size, String mau) {
        for (ProductDetail sp : this.pdds.getProduct_Detail_Sell()) {
            if (ten.equalsIgnoreCase(sp.getProductId().getName_product()) && size.equalsIgnoreCase(sp.getSizeId().getNameSize()) && mau.equalsIgnoreCase(sp.getColorId().getNameColor())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTT = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName_Product = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbVatLieu = new javax.swing.JComboBox<>();
        txtGia = new javax.swing.JTextField();
        cbbDoDay = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        cbbKieu = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnXoaCT = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbMau = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnThemCT = new javax.swing.JButton();
        btnSuaChiTiet = new javax.swing.JButton();
        cbbSp = new javax.swing.JComboBox<>();
        btnNgungBan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangChiTiet = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        txtName_Attribute = new javax.swing.JTextField();
        rdoSize = new javax.swing.JRadioButton();
        rdoColor = new javax.swing.JRadioButton();
        rdoMaterial = new javax.swing.JRadioButton();
        rdoThickness = new javax.swing.JRadioButton();
        rdoCustom = new javax.swing.JRadioButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Tên sản phẩm:");

        jLabel3.setText("Vật liệu:");

        jLabel4.setText("Giá:");

        jLabel5.setText("Độ dày:");

        cbbVatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbDoDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Mô tả:");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel7.setText("Kiểu dáng:");

        cbbKieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Name product", "Created at", "Updated at", "Custom", "Price", "Material", "Thicknes", "Describe"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBang);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Ngừng kinh doanh");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClen.setText("Xóa form");
        btnClen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClenActionPerformed(evt);
            }
        });

        jLabel2.setText("gsm");

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(">");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("danh sách sản phẩm ngừng kinh doanh");
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
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtName_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbDoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbKieu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbVatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClen, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtName_Product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnClen)
                        .addComponent(btnXoa)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(cbbVatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbbDoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbbKieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("San pham", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnXoaCT.setText("Clear form");
        btnXoaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTActionPerformed(evt);
            }
        });

        jLabel9.setText("Size:");

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Coler:");

        cbbMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Số  lượng:");

        btnThemCT.setText("Thêm");
        btnThemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTActionPerformed(evt);
            }
        });

        btnSuaChiTiet.setText("Sửa");
        btnSuaChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChiTietActionPerformed(evt);
            }
        });

        cbbSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnNgungBan.setText("Ngừng bán");
        btnNgungBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgungBanActionPerformed(evt);
            }
        });

        jLabel8.setText("Tên sản phẩm: ");

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "ten sp", "do day", "nguyen lieu", "kieu"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSP);

        tblBangChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "size", "màu sắc", "Ngày tạo", "Ngày sửa", "Số lượng"
            }
        ));
        tblBangChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBangChiTiet);

        jButton5.setText("<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setText(">");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("<");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText(">");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Các sản phẩm ngừng bán");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbSp, 0, 288, Short.MAX_VALUE)
                                    .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbMau, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoLuong))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXoaCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNgungBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuaChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton13)))
                        .addGap(0, 252, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnThemCT)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaChiTiet))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNgungBan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(btnXoaCT)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton11))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("San pham chi tiet", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setText("Ngừng hoạt động");

        jButton9.setText("Clear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel12.setText("Thêm thuộc tính sản phẩm:");

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "name", "Created_at", "Updated_at"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThuocTinh);

        btgTT.add(rdoSize);
        rdoSize.setSelected(true);
        rdoSize.setText("Size");
        rdoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeActionPerformed(evt);
            }
        });

        btgTT.add(rdoColor);
        rdoColor.setText("Màu");
        rdoColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoColorActionPerformed(evt);
            }
        });

        btgTT.add(rdoMaterial);
        rdoMaterial.setText("Vật liệu");
        rdoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMaterialActionPerformed(evt);
            }
        });

        btgTT.add(rdoThickness);
        rdoThickness.setText("Độ dày");
        rdoThickness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThicknessActionPerformed(evt);
            }
        });

        btgTT.add(rdoCustom);
        rdoCustom.setText("Kiểu");
        rdoCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCustomActionPerformed(evt);
            }
        });

        jButton6.setText("Thêm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Sửa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtName_Attribute, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoColor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoMaterial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoThickness)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoCustom))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9)))
                        .addGap(0, 202, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtName_Attribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoSize)
                    .addComponent(rdoMaterial)
                    .addComponent(rdoColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoThickness)
                    .addComponent(rdoCustom))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuoc tinh", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        int row = tblBang.getSelectedRow();
        idSP = tblBang.getValueAt(row, 0).toString();
        txtName_Product.setText(tblBang.getValueAt(row, 1).toString());
        cbbVatLieu.setSelectedItem(tblBang.getValueAt(row, 6).toString());
        txtGia.setText(tblBang.getValueAt(row, 5).toString());
        String doDay = tblBang.getValueAt(row, 7).toString();
        String pattern = "(\\d+)gsm";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(doDay);
        while (matcher.find()) {
            String match = matcher.group(1);
            cbbDoDay.setSelectedItem(match.toString());
        }
        cbbKieu.setSelectedItem(tblBang.getValueAt(row, 4).toString());
        txtMoTa.setText(tblBang.getValueAt(row, 8).toString());
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        model.entity.Product sp = form();
        if (sp == null) {
            return;
        }
        if (checkSP(txtName_Product.getText().trim()) == false) {
            return;
        }
        if (checkSPKOSD(txtName_Product.getText().trim()) == false) {
            return;
        }
        if (pds.them(sp) == true) {
            System.out.println("ten: " + sp.getName_product());
            System.out.println("vat lieu id: " + sp.getMaterial_id().getNameMaterial());
            System.out.println("gia" + sp.getProduct_price());
            System.out.println("do day: " + sp.getThickness_id().getGsm());
            System.out.println("kieu dang: " + sp.getCustome_id().getNameCustom());
            System.out.println("mo ta:" + sp.getDescription());
            JOptionPane.showMessageDialog(this, "Them thanh cong");
            this.loadSPNext();
            this.loadSPPhu();
            this.loadCBBSP();
        } else {
            JOptionPane.showMessageDialog(this, "Them that bai");
            return;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        model.entity.Product sp = form();
        if (sp == null) {
            return;
        }
        if (this.pds.sua(idSP, sp) == true) {
            JOptionPane.showMessageDialog(this, "Da sua thanh cong");
            this.loadCT();
            this.loadSPNext();
            this.loadSPPhu();
            this.loadCBBSP();
        } else {
            JOptionPane.showMessageDialog(this, "Da sua that bai");
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (txtName_Product.getText().trim().equalsIgnoreCase("")) {
            return;
        }
        if (this.pds.xoa(txtName_Product.getText().trim()) == true) {
            JOptionPane.showMessageDialog(this, "Da xoa thanh cong");
            this.loadCT();
            this.loadSPNext();
            this.loadSPPhu();
            this.loadCBBSP();
            this.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Da xoa that bai");
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnXoaCTActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        // TODO add your handling code here:
        this.loadMau();
    }//GEN-LAST:event_rdoColorActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();
        if (row < 0) {
            return;
        }
        idSP = tblSP.getValueAt(row, 0).toString();
        this.loadCT();
    }//GEN-LAST:event_tblSPMouseClicked

    private void tblBangChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangChiTietMouseClicked
        // TODO add your handling code here:
        int row = this.tblBangChiTiet.getSelectedRow();
        cbbSize.setSelectedItem(tblBangChiTiet.getValueAt(row, 1).toString());
        cbbMau.setSelectedItem(tblBangChiTiet.getValueAt(row, 2).toString());
        txtSoLuong.setText(tblBangChiTiet.getValueAt(row, 5).toString());
        idSPCT = tblBangChiTiet.getValueAt(row, 0).toString();
    }//GEN-LAST:event_tblBangChiTietMouseClicked

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed
        // TODO add your handling code here:
        this.loadSize();
    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMaterialActionPerformed
        // TODO add your handling code here:
        this.loadVatLieu();
    }//GEN-LAST:event_rdoMaterialActionPerformed

    private void rdoThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThicknessActionPerformed
        // TODO add your handling code here:
        this.loadDoDay();
    }//GEN-LAST:event_rdoThicknessActionPerformed

    private void rdoCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCustomActionPerformed
        // TODO add your handling code here:
        this.loadKieu();
    }//GEN-LAST:event_rdoCustomActionPerformed

    private void btnClenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClenActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClenActionPerformed

    private void btnThemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTActionPerformed
        // TODO add your handling code here:
        ProductDetail spct = formct();
        if (this.checkSPCTNB(cbbSp.getSelectedItem().toString(), cbbSize.getSelectedItem().toString(), cbbMau.getSelectedItem().toString()) == false) {
            return;
        }

        if (this.checkSPCTCB(cbbSp.getSelectedItem().toString(), cbbSize.getSelectedItem().toString(), cbbMau.getSelectedItem().toString()) == false) {
            return;
        }

        if (spct == null) {
            return;
        }
        if (pdds.them(spct) == true) {
            JOptionPane.showMessageDialog(this, "them thanh cong");
            this.loadCT();
        } else {
            JOptionPane.showMessageDialog(this, "them that bai");
            return;
        }
    }//GEN-LAST:event_btnThemCTActionPerformed

    private void btnSuaChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChiTietActionPerformed
        // TODO add your handling code here:
        int row = tblBangChiTiet.getSelectedRow();
        if (row < 0) {
            return;
        }
        ProductDetail spct = formct();
        if (this.pdds.sua(idSPCT, spct) == true) {
            JOptionPane.showMessageDialog(this, "Da sua thanh cong");
            this.loadCT();
        } else {
            JOptionPane.showMessageDialog(this, "Sua that bai");
            return;
        }
    }//GEN-LAST:event_btnSuaChiTietActionPerformed

    private void btnNgungBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgungBanActionPerformed
        // TODO add your handling code here:
        int row = tblBangChiTiet.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (this.pdds.xoa(idSPCT) == true) {
            JOptionPane.showMessageDialog(this, "Da ngung ban");
            this.loadCT();
            this.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Da ngung ba that bai");
            return;
        }
    }//GEN-LAST:event_btnNgungBanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String ten = txtName_Attribute.getText().trim();
        if (ten.equals("")) {
            return;
        }
        if (rdoSize.isSelected() == true) {
            Size s = new Size(ten);

            if (this.ss.Insert(s)) {
                JOptionPane.showMessageDialog(this, "Da them size.");
                this.loadSize();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoColor.isSelected() == true) {
            Color c = new Color(ten);

            if (this.cls.them(c)) {
                JOptionPane.showMessageDialog(this, "Da them mau");
                this.loadMau();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoMaterial.isSelected() == true) {
            Material m = new Material(ten);
            if (m == null) {
                return;
            }
            if (this.mts.them(m)) {
                JOptionPane.showMessageDialog(this, "Da them vat lieu.");
                this.loadVatLieu();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoThickness.isSelected() == true) {
            try {
                int dd = Integer.parseInt(ten);
                Thickness t = new Thickness(dd);
                if (t == null) {
                    return;
                }
                if (this.tns.them(t)) {
                    JOptionPane.showMessageDialog(this, "Da them do day.");
                    this.loadDoDay();
                    this.loadcbbkieu();
                    this.loadcbbVL();
                    this.loadcbbDD();
                    this.loadCBBS();
                    this.loadCBBCL();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (rdoCustom.isSelected() == true) {
            Custom c = new Custom(ten);
            if (c == null) {
                return;
            }
            if (this.cts.them(c)) {
                JOptionPane.showMessageDialog(this, "Da them kieu.");
                this.loadKieu();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String ten = txtName_Attribute.getText().trim();
        if (ten.equals("")) {
            return;
        }
        if (rdoSize.isSelected() == true) {
            Size s = new Size(ten);

            if (this.ss.Update(idTT, s)) {
                JOptionPane.showMessageDialog(this, "Da sua size.");
                this.loadSize();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoColor.isSelected() == true) {
            Color c = new Color(ten);

            if (this.cls.sua(idTT, c)) {
                JOptionPane.showMessageDialog(this, "Da them mau");
                this.loadMau();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoMaterial.isSelected() == true) {
            Material m = new Material(ten);
            if (m == null) {
                return;
            }
            if (this.mts.sua(idTT, m)) {
                JOptionPane.showMessageDialog(this, "Da them vat lieu.");
                this.loadVatLieu();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        } else if (rdoThickness.isSelected() == true) {
            try {
                int dd = Integer.parseInt(ten);
                Thickness t = new Thickness(dd);
                if (t == null) {
                    return;
                }
                if (this.tns.sua(idTT, t)) {
                    JOptionPane.showMessageDialog(this, "Da them do day.");
                    this.loadDoDay();
                    this.loadcbbkieu();
                    this.loadcbbVL();
                    this.loadcbbDD();
                    this.loadCBBS();
                    this.loadCBBCL();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (rdoCustom.isSelected() == true) {
            Custom c = new Custom(ten);
            if (c == null) {
                return;
            }
            if (this.cts.sua(idTT, c)) {
                JOptionPane.showMessageDialog(this, "Da them kieu.");
                this.loadKieu();
                this.loadcbbkieu();
                this.loadcbbVL();
                this.loadcbbDD();
                this.loadCBBS();
                this.loadCBBCL();
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        int row = tblThuocTinh.getSelectedRow();
        idTT = tblThuocTinh.getValueAt(row, 0).toString();
        txtName_Attribute.setText(tblThuocTinh.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        maxProduct_tab1 -= 10;
        minProduct_tab1 = maxProduct_tab1 - 9;
        if(minProduct_tab1 < 1){
            return;
        }
        this.loadSPNext();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        minProduct_tab1 = maxProduct_tab1 + 1;
        maxProduct_tab1 += 10;
        this.loadSPNext();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        maxProduct_tab2 -= 10;
        minProduct_tab2 = maxProduct_tab2 - 9;
        if(minProduct_tab2 < 1){
            return;
        }
        this.loadSPPhu();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        minProduct_tab2 = maxProduct_tab2 + 1;
        maxProduct_tab2 += 10;
        this.loadSPPhu();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        maxProduct_detail -= 10;
        minProduct_detail = maxProduct_detail - 9;
        if(minProduct_detail < 1){
            return;
        }
        this.loadCT();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        minProduct_detail = maxProduct_detail + 1;
        maxProduct_detail += 10;
        this.loadCT();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        Product_Deteil_stop_sellingJDialog prs = new Product_Deteil_stop_sellingJDialog(frame, true);
        prs.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Product_stop_sellingJdialog st = new Product_stop_sellingJdialog(frame, true);
        st.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTT;
    private javax.swing.JButton btnClen;
    private javax.swing.JButton btnNgungBan;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaChiTiet;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaCT;
    private javax.swing.JComboBox<String> cbbDoDay;
    private javax.swing.JComboBox<String> cbbKieu;
    private javax.swing.JComboBox<String> cbbMau;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbSp;
    private javax.swing.JComboBox<String> cbbVatLieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoCustom;
    private javax.swing.JRadioButton rdoMaterial;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JRadioButton rdoThickness;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblBangChiTiet;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtName_Attribute;
    private javax.swing.JTextField txtName_Product;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
