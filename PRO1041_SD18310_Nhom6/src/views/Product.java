/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

    File file = new File("");
    DefaultComboBoxModel dcm;

//up date them 2 thang moi 
    int minProduct_tab1 = 1;
    int maxProduct_tab1 = 10;
    int minProduct_tab2 = 1;
    int maxProduct_tab2 = 10;
    int minProduct_detail = 1;
    int maxProduct_detail = 10;
    int minProduct_tab3 = 1;
    int maxProduct_tab3 = 10;
    //them cai nay ngay 28//11

    int maxProduct_Stop_Sell = 10;
    int minProduct_Stop_Sell = 1;
    int maxProduct_Detail_Stop_Sell = 10;
    int minProduct_Detail_Stop_Sell = 1;

    //them 1/12
    int maxAttribute = 10;
    int minAttribute = 1;
    //het
    //cai nay viet trc r
    String idProduct;
    String idProduct_Extra;
    String idProduct_Detail;
    String idTT;
    String idProduct_Stop_Sell;
    String idProduct_Detail_Stop_Sell;
    String nameImage_Product;
    String nameImage_Product_Detail;

    //het
    /**
     * Creates new form Product
     */
    public Product() {
        initComponents();
        pds = new ProductImple();
        pdds = new ProductDetailImple();
        mts = new MaterialImple();
        tns = new ThicknessImple();
        cts = new CustomImple();
        ss = new SizeImple();
        cls = new ColorImple();

        this.load_Product();
//        this.load_Product_Extra();
        this.loadProduct_Stop();
        this.loadProduct_Deteail_Stop_Sell();
        this.loadProduct_Has_No_Category_Yet();

        this.loadSize();

        this.loadcbbCustom();
        this.loadcbbMaterial();
        this.loadcbbThickness();
        this.loadcbbSize();
        this.loadcbbColor();
//        this.loadcbbProduct();
        System.out.println(idProduct_Extra);

//        this.pnlProduct_Detail.setVisible(false);
    }

    public void load_Product() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (model.entity.Product sp : this.pds.getNext(minProduct_tab1, maxProduct_tab1)) {
            Object[] ob = {
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm",};
            dtm.addRow(ob);
        }
    }
//xoa cai nay
//    public void load_Product_Extra() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Extra.getModel();
//        dtm.setRowCount(0);
//
//        for (model.entity.Product sp : this.pds.getNext(minProduct_tab2, maxProduct_tab2)) {
//            Object[] ob = {
//                sp.getId(),
//                sp.getName_product(),
//                sp.getCustome_id().getNameCustom(),
//                sp.getMaterial_id().getNameMaterial(),
//                sp.getThickness_id().getGsm() + "gsm",};
//            dtm.addRow(ob);
//        }
//    }

    //  cai nay tam bo
//    public void loadCT() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tblBangChiTiet.getModel();
//        dtm.setRowCount(0);
//        String tt = "";
//        for (ProductDetail spct : this.pdds.getProductDetails_id(idSP)) {
//
//            Object[] ob = {
//                spct.getId(),
//                spct.getSizeId().getNameSize(),
//                spct.getColorId().getNameColor(),
//                spct.getCreatedAt(),
//                spct.getUpdatedAt(),
//                spct.getQuantity()
//            };
//            dtm.addRow(ob);
//        }
//    }
    //thay the bang cai nay ngay 28//11
    public void load_Product_Detail() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Detail1.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (ProductDetail spct : this.pdds.getProductDetail_Selling_Next(idProduct_Extra, minProduct_detail, maxProduct_detail)) {
            Object[] ob = {
                spct.getId(),
                spct.getSizeId().getNameSize(),
                spct.getColorId().getNameColor(),
                spct.getQuantity()
            };
            dtm.addRow(ob);
        }
    }

    public void loadSize() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Size d : this.ss.getSize_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                d.getId(),
                d.getNameSize(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadColer() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (Color d : this.cls.getColor_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                d.getId(),
                d.getNameColor(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadMaterial() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Material d : this.mts.getMaterial_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                d.getId(),
                d.getNameMaterial(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadThickness() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Thickness d : this.tns.getThickness_sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                d.getId(),
                d.getGsm(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadCustom() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        String tt = "";

        for (Custom d : this.cts.getCustom_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                d.getId(),
                d.getNameCustom(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
        }
    }

    public void loadcbbCustom() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Custom ct : this.cts.getAll()) {
            dcm.addElement(ct.getNameCustom());
        }
        cbbCustom.setModel(dcm);
    }

    public void loadcbbMaterial() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Material nl : this.mts.getAll()) {
            dcm.addElement(nl.getNameMaterial());
        }
        cbbMaterial.setModel(dcm);
    }

    public void loadcbbThickness() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Thickness dd : this.tns.getAll()) {
            String doDay = String.valueOf(dd.getGsm());
            dcm.addElement(doDay);
        }
        cbbThickness.setModel(dcm);
    }

    public void loadcbbSize() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Size s : this.ss.getAll()) {
            String ten = String.valueOf(s.getNameSize());
            dcm.addElement(ten);
        }
        cbbSize1.setModel(dcm);
    }

    public void loadcbbColor() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Color m : this.cls.getAll()) {
            String ten = String.valueOf(m.getNameColor());
            dcm.addElement(ten);
        }
        cbbColor1.setModel(dcm);
    }
    //xoa cai nay
//    public void loadcbbProduct() {
//        dcm = new DefaultComboBoxModel();
//        dcm.removeAllElements();
//        for (model.entity.Product pr : this.pds.getProduct_sell()) {
//            String ten = String.valueOf(pr.getName_product());
//            dcm.addElement(ten);
//        }
//        cbbProduct.setModel(dcm);
//    }

    public void clear() {
        txtName_Product.setText("");
        cbbMaterial.setSelectedIndex(0);
        txtPrice.setText("");
        cbbThickness.setSelectedIndex(0);
        cbbCustom.setSelectedIndex(0);
        txtDescribe.setText("");
//        cbbProduct.setSelectedIndex(0);
        cbbColor1.setSelectedIndex(0);
        cbbSize1.setSelectedIndex(0);
        txtQuantity1.setText("");
        txtName_Attribute.setText("");
        rdoSize.setSelected(true);
        this.loadSize();
    }

    public ProductDetail formct() {
        String ten = txtName_Product.getText();
        String size = cbbSize1.getSelectedItem().toString().trim();
        String mau = cbbColor1.getSelectedItem().toString().trim();
        String sl = txtQuantity1.getText().trim();
        if (ten.equals("")) {
            JOptionPane.showMessageDialog(this, "chua co san pham de them");
            return null;
        }
        try {
            int soLuong = Integer.parseInt(sl);
            try {
                byte[] img = readImageFile(nameImage_Product_Detail);
                ProductDetail spct = new ProductDetail(soLuong, new Color(mau), new model.entity.Product(ten), new Size(size), img);
                return spct;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Loi anh");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public model.entity.Product form() {
        String ten = txtName_Product.getText().trim();
        String vatLieu = cbbMaterial.getSelectedItem().toString();
        String gia = txtPrice.getText().trim();
        String doDay = cbbThickness.getSelectedItem().toString();
        String kieuDang = cbbCustom.getSelectedItem().toString();
        String moTa = txtDescribe.getText().trim();
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
            try {
                byte[] img = readImageFile(nameImage_Product);
                model.entity.Product sp = new model.entity.Product(tien, new Custom(kieuDang), new Material(vatLieu), new Thickness(doDaySo), moTa, ten, img);
                return sp;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Loi anh");
                e.printStackTrace();
            }

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
    //them cai nay ngay 28//11

    public boolean checkNull_Table(ArrayList<?> myList) {
        return myList != null && !myList.isEmpty();
    }

    public void loadProduct_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduc_Stop_sell.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (model.entity.Product sp : this.pds.getNext_Product_Stop_selling(minProduct_Stop_Sell, maxProduct_Stop_Sell)) {
            Object[] ob = {
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm"
            };
            dtm.addRow(ob);
        }
    }

    public void loadProduct_Deteail_Stop_Sell() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Detail_Stop_Sell.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (ProductDetail spct : this.pdds.getRestore_Product_Detail_stop_selling(minProduct_Detail_Stop_Sell, maxProduct_Detail_Stop_Sell)) {
            Object[] ob = {
                spct.getId(),
                spct.getProductId().getName_product(),
                spct.getSizeId().getNameSize(),
                spct.getColorId().getNameColor(),
                spct.getQuantity()
            };
            dtm.addRow(ob);
        }
    }

    //them vao ngay 29/11
    public void loadProduct_Has_No_Category_Yet() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Has_No_Category_Yet.getModel();
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

    //them moi 1/12
    private static byte[] readImageFile(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        }
        return imageData;
    }

    private static BufferedImage getImageFromByteArray(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        cbbMaterial = new javax.swing.JComboBox<>();
        txtPrice = new javax.swing.JTextField();
        cbbThickness = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescribe = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        cbbCustom = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        btnAdd_Product = new javax.swing.JButton();
        btnFix_Produc = new javax.swing.JButton();
        btnStop_Sell_Product = new javax.swing.JButton();
        btnClear_Product = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnPre__Product = new javax.swing.JButton();
        btnNext__Product = new javax.swing.JButton();
        pnlProduct_Detail = new javax.swing.JPanel();
        btnAdd_Product_Detail1 = new javax.swing.JButton();
        btnFix_Product_Detail1 = new javax.swing.JButton();
        btnStop_Sell_Product_Detail1 = new javax.swing.JButton();
        btnClear_Product_Detail1 = new javax.swing.JButton();
        txtQuantity1 = new javax.swing.JTextField();
        cbbColor1 = new javax.swing.JComboBox<>();
        cbbSize1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnPre_Product_Detail1 = new javax.swing.JButton();
        btnNext_Product_Detail1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblProduct_Detail1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblImage_Detail = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnStop_Working_Attribute = new javax.swing.JButton();
        btnClear_Attribute = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAttribute = new javax.swing.JTable();
        txtName_Attribute = new javax.swing.JTextField();
        rdoSize = new javax.swing.JRadioButton();
        rdoColor = new javax.swing.JRadioButton();
        rdoMaterial = new javax.swing.JRadioButton();
        rdoThickness = new javax.swing.JRadioButton();
        rdoCustom = new javax.swing.JRadioButton();
        btnAdd_Attribute = new javax.swing.JButton();
        btnFix_Attribute = new javax.swing.JButton();
        btnNext_Attribute = new javax.swing.JButton();
        btnPre_Attribute = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnPre_Product_Stop_Sell = new javax.swing.JButton();
        btnNext_Product_Stop_Sell = new javax.swing.JButton();
        btnRestore_Product = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProduc_Stop_sell = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnRestore_Product_Detail_Stop_Sell = new javax.swing.JButton();
        btnNext_Product_Detail_Stop_Sell = new javax.swing.JButton();
        btnPre_Product_Detail_Stop_Sell = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProduct_Detail_Stop_Sell = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProduct_Has_No_Category_Yet = new javax.swing.JTable();
        btnPre_Product_Has_No_Category_Yet = new javax.swing.JButton();
        btnNext_Product_Has_No_Category_Yet = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Tên sản phẩm:");

        jLabel3.setText("Vật liệu:");

        jLabel4.setText("Giá:");

        jLabel5.setText("Độ dày:");

        cbbMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThickness.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Mô tả:");

        txtDescribe.setColumns(20);
        txtDescribe.setRows(5);
        jScrollPane1.setViewportView(txtDescribe);

        jLabel7.setText("Kiểu dáng:");

        cbbCustom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tên sản phẩm", "Kiểu", "Gía", "Nguyên liệu", "Độ dày"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduct);

        btnAdd_Product.setText("Thêm");
        btnAdd_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_ProductActionPerformed(evt);
            }
        });

        btnFix_Produc.setText("Sửa");
        btnFix_Produc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_ProducActionPerformed(evt);
            }
        });

        btnStop_Sell_Product.setText("Ngừng bán");
        btnStop_Sell_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Sell_ProductActionPerformed(evt);
            }
        });

        btnClear_Product.setText("Làm mới");
        btnClear_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_ProductActionPerformed(evt);
            }
        });

        jLabel2.setText("gsm");

        btnPre__Product.setText("<");
        btnPre__Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre__ProductActionPerformed(evt);
            }
        });

        btnNext__Product.setText(">");
        btnNext__Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext__ProductActionPerformed(evt);
            }
        });

        pnlProduct_Detail.setBackground(new java.awt.Color(255, 255, 255));
        pnlProduct_Detail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlProduct_Detail.setForeground(new java.awt.Color(255, 255, 255));

        btnAdd_Product_Detail1.setText("Thêm");
        btnAdd_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_Product_Detail1ActionPerformed(evt);
            }
        });

        btnFix_Product_Detail1.setText("Sửa");
        btnFix_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_Product_Detail1ActionPerformed(evt);
            }
        });

        btnStop_Sell_Product_Detail1.setText("Ngừng bán");
        btnStop_Sell_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Sell_Product_Detail1ActionPerformed(evt);
            }
        });

        btnClear_Product_Detail1.setText("Làm mới");
        btnClear_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_Product_Detail1ActionPerformed(evt);
            }
        });

        cbbColor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Kích cỡ:");

        jLabel15.setText("Màu:");

        jLabel16.setText("Số  lượng:");

        btnPre_Product_Detail1.setText("<");
        btnPre_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Detail1ActionPerformed(evt);
            }
        });

        btnNext_Product_Detail1.setText(">");
        btnNext_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Detail1ActionPerformed(evt);
            }
        });

        tblProduct_Detail1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Kích cỡ", "màu sắc", "Số lượng"
            }
        ));
        tblProduct_Detail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Detail1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblProduct_Detail1);

        lblImage_Detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImage_DetailMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlProduct_DetailLayout = new javax.swing.GroupLayout(pnlProduct_Detail);
        pnlProduct_Detail.setLayout(pnlProduct_DetailLayout);
        pnlProduct_DetailLayout.setHorizontalGroup(
            pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantity1)
                                    .addComponent(cbbColor1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbSize1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addComponent(btnAdd_Product_Detail1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFix_Product_Detail1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStop_Sell_Product_Detail1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear_Product_Detail1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProduct_DetailLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProduct_DetailLayout.setVerticalGroup(
            pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbbSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbbColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd_Product_Detail1)
                            .addComponent(btnFix_Product_Detail1)
                            .addComponent(btnStop_Sell_Product_Detail1)
                            .addComponent(btnClear_Product_Detail1)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext_Product_Detail1)
                    .addComponent(btnPre_Product_Detail1))
                .addContainerGap())
        );

        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear_Product))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAdd_Product)
                                .addGap(12, 12, 12)
                                .addComponent(btnFix_Produc)
                                .addGap(12, 12, 12)
                                .addComponent(btnStop_Sell_Product))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtName_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbbThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cbbCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlProduct_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPre__Product, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext__Product, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addGap(96, 96, 96)
                        .addComponent(btnClear_Product))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtName_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(cbbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbThickness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel2)))
                                .addGap(16, 16, 16)
                                .addComponent(cbbCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd_Product)
                            .addComponent(btnFix_Produc)
                            .addComponent(btnStop_Sell_Product)))
                    .addComponent(pnlProduct_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext__Product)
                    .addComponent(btnPre__Product))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnStop_Working_Attribute.setText("Xóa");
        btnStop_Working_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Working_AttributeActionPerformed(evt);
            }
        });

        btnClear_Attribute.setText("Làm mới");
        btnClear_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_AttributeActionPerformed(evt);
            }
        });

        jLabel12.setText("Thêm thuộc tính sản phẩm:");

        tblAttribute.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Tên", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblAttribute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAttributeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblAttribute);

        btgTT.add(rdoSize);
        rdoSize.setSelected(true);
        rdoSize.setText("Kích cỡ");
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
        rdoMaterial.setText("Nguyên liệu");
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

        btnAdd_Attribute.setText("Thêm");
        btnAdd_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_AttributeActionPerformed(evt);
            }
        });

        btnFix_Attribute.setText("Sửa");
        btnFix_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_AttributeActionPerformed(evt);
            }
        });

        btnNext_Attribute.setText(">");
        btnNext_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_AttributeActionPerformed(evt);
            }
        });

        btnPre_Attribute.setText("<");
        btnPre_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_AttributeActionPerformed(evt);
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
                                .addComponent(btnAdd_Attribute)
                                .addGap(18, 18, 18)
                                .addComponent(btnFix_Attribute)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnStop_Working_Attribute)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear_Attribute)))
                        .addGap(0, 429, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Attribute, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext_Attribute, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnAdd_Attribute)
                    .addComponent(btnFix_Attribute)
                    .addComponent(btnStop_Working_Attribute)
                    .addComponent(btnClear_Attribute))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext_Attribute)
                    .addComponent(btnPre_Attribute))
                .addContainerGap(290, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính", jPanel4);

        btnPre_Product_Stop_Sell.setText("<");
        btnPre_Product_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Stop_SellActionPerformed(evt);
            }
        });

        btnNext_Product_Stop_Sell.setText(">");
        btnNext_Product_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Stop_SellActionPerformed(evt);
            }
        });

        btnRestore_Product.setText(" Restore");
        btnRestore_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore_ProductActionPerformed(evt);
            }
        });

        tblProduc_Stop_sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tên sản phẩm", "Kiểu", "Gía", "Nguyên liệu", "Độ dày"
            }
        ));
        tblProduc_Stop_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduc_Stop_sellMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblProduc_Stop_sell);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestore_Product))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestore_Product)
                    .addComponent(btnPre_Product_Stop_Sell)
                    .addComponent(btnNext_Product_Stop_Sell))
                .addContainerGap(501, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm ngừng bán", jPanel5);

        btnRestore_Product_Detail_Stop_Sell.setText(" Restore");
        btnRestore_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        btnNext_Product_Detail_Stop_Sell.setText(">");
        btnNext_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        btnPre_Product_Detail_Stop_Sell.setText("<");
        btnPre_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        tblProduct_Detail_Stop_Sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Tên sản phẩm", "Kích cỡ", "Màu", "Số lượng"
            }
        ));
        tblProduct_Detail_Stop_Sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Detail_Stop_SellMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblProduct_Detail_Stop_Sell);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestore_Product_Detail_Stop_Sell)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext_Product_Detail_Stop_Sell)
                        .addComponent(btnPre_Product_Detail_Stop_Sell))
                    .addComponent(btnRestore_Product_Detail_Stop_Sell))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết ngừng bán", jPanel6);

        tblProduct_Has_No_Category_Yet.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduct_Has_No_Category_Yet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Has_No_Category_YetMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblProduct_Has_No_Category_Yet);

        btnPre_Product_Has_No_Category_Yet.setText("<");
        btnPre_Product_Has_No_Category_Yet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Has_No_Category_YetActionPerformed(evt);
            }
        });

        btnNext_Product_Has_No_Category_Yet.setText(">");
        btnNext_Product_Has_No_Category_Yet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Has_No_Category_YetActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Product has no category yet");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Has_No_Category_Yet, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Has_No_Category_Yet, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPre_Product_Has_No_Category_Yet)
                    .addComponent(btnNext_Product_Has_No_Category_Yet))
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh mục", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
        this.pnlProduct_Detail.setVisible(true);
        int row_pr = tblProduct.getSelectedRow();
        model.entity.Product pr = this.pds.getNext(minProduct_tab1, maxProduct_tab1).get(row_pr);
        idProduct = tblProduct.getValueAt(row_pr, 0).toString();
        txtName_Product.setText(pr.getName_product());
        cbbMaterial.setSelectedItem(pr.getMaterial_id().getNameMaterial());
        txtPrice.setText(pr.getProduct_price().toString());
        cbbThickness.setSelectedItem(String.valueOf(pr.getThickness_id().getGsm()));
        cbbCustom.setSelectedItem(pr.getCustome_id().getNameCustom());
        txtDescribe.setText(pr.getDescription());

        minProduct_detail = 1;
        maxProduct_detail = 10;
        idProduct_Extra = tblProduct.getValueAt(row_pr, 0).toString();
        //xuat anh
        byte[] retrievedImageData = pr.getImage_Type();
        BufferedImage image = getImageFromByteArray(retrievedImageData);
        if (image != null) {
            lblImage.setText("");
            int width = lblImage.getWidth();
            int height = lblImage.getHeight();
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(width, height, 0));
            lblImage.setIcon(imageIcon);
        } else {
            lblImage.setIcon(null);
            lblImage.setText("Không thể hiển thị ảnh.");
        }

        this.load_Product_Detail();
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnAdd_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_ProductActionPerformed
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
            this.load_Product();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
        } else {
            JOptionPane.showMessageDialog(this, "Them that bai");
            return;
        }
    }//GEN-LAST:event_btnAdd_ProductActionPerformed

    private void btnFix_ProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_ProducActionPerformed
        // TODO add your handling code here:
        model.entity.Product sp = form();
        if (sp == null) {
            return;
        }
        if (this.pds.sua(idProduct, sp) == true) {
            JOptionPane.showMessageDialog(this, "Da sua thanh cong");
            this.load_Product_Detail();
            this.load_Product();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
        } else {
            JOptionPane.showMessageDialog(this, "Da sua that bai");
            return;
        }
    }//GEN-LAST:event_btnFix_ProducActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        // TODO add your handling code here:
        minAttribute = 1;
        maxAttribute = 10;
        this.loadColer();
    }//GEN-LAST:event_rdoColorActionPerformed

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed
        // TODO add your handling code here:
        minAttribute = 1;
        maxAttribute = 10;
        this.loadSize();
    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMaterialActionPerformed
        // TODO add your handling code here:
        minAttribute = 1;
        maxAttribute = 10;
        this.loadMaterial();
    }//GEN-LAST:event_rdoMaterialActionPerformed

    private void rdoThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThicknessActionPerformed
        // TODO add your handling code here:
        minAttribute = 1;
        maxAttribute = 10;
        this.loadThickness();
    }//GEN-LAST:event_rdoThicknessActionPerformed

    private void rdoCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCustomActionPerformed
        // TODO add your handling code here:
        minAttribute = 1;
        maxAttribute = 10;
        this.loadCustom();
    }//GEN-LAST:event_rdoCustomActionPerformed

    private void btnClear_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_ProductActionPerformed
        // TODO add your handling code here:
        this.clear();
        this.pnlProduct_Detail.setVisible(false);
    }//GEN-LAST:event_btnClear_ProductActionPerformed

    private void btnAdd_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_AttributeActionPerformed
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
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoColor.isSelected() == true) {
            Color c = new Color(ten);

            if (this.cls.them(c)) {
                JOptionPane.showMessageDialog(this, "Da them mau");
                this.loadColer();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoMaterial.isSelected() == true) {
            Material m = new Material(ten);
            if (m == null) {
                return;
            }
            if (this.mts.them(m)) {
                JOptionPane.showMessageDialog(this, "Da them vat lieu.");
                this.loadMaterial();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
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
                    this.loadThickness();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
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
                this.loadCustom();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        }
    }//GEN-LAST:event_btnAdd_AttributeActionPerformed

    private void btnFix_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_AttributeActionPerformed
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
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoColor.isSelected() == true) {
            Color c = new Color(ten);

            if (this.cls.sua(idTT, c)) {
                JOptionPane.showMessageDialog(this, "Da them mau");
                this.loadColer();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoMaterial.isSelected() == true) {
            Material m = new Material(ten);
            if (m == null) {
                return;
            }
            if (this.mts.sua(idTT, m)) {
                JOptionPane.showMessageDialog(this, "Da them vat lieu.");
                this.loadMaterial();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
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
                    this.loadThickness();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
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
                this.loadCustom();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        }
    }//GEN-LAST:event_btnFix_AttributeActionPerformed

    private void btnClear_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_AttributeActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClear_AttributeActionPerformed

    private void tblAttributeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAttributeMouseClicked
        // TODO add your handling code here:
        int row = tblAttribute.getSelectedRow();
        idTT = tblAttribute.getValueAt(row, 0).toString();
        txtName_Attribute.setText(tblAttribute.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tblAttributeMouseClicked

    private void btnPre__ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre__ProductActionPerformed
        // TODO add your handling code here:
        maxProduct_tab1 -= 10;
        minProduct_tab1 = maxProduct_tab1 - 9;
        if (minProduct_tab1 < 1) {
            maxProduct_tab1 = 10;
            minProduct_tab1 = 1;
            return;
        }
        this.load_Product();
    }//GEN-LAST:event_btnPre__ProductActionPerformed

    private void btnNext__ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext__ProductActionPerformed
        // TODO add your handling code here:
        minProduct_tab1 = maxProduct_tab1 + 1;
        maxProduct_tab1 += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(minProduct_tab1, maxProduct_tab1));
        if (checkList) {
            this.load_Product();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_tab1 -= 10;
            minProduct_tab1 = maxProduct_tab1 - 9;
            return;
        }

    }//GEN-LAST:event_btnNext__ProductActionPerformed

    private void btnPre_Product_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Stop_SellActionPerformed
        // TODO add your handling code here:
        maxProduct_Stop_Sell -= 10;
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
        if (maxProduct_Stop_Sell < 1) {
            maxProduct_Stop_Sell = 10;
            minProduct_Detail_Stop_Sell = 1;
            return;
        }
        this.loadProduct_Stop();
    }//GEN-LAST:event_btnPre_Product_Stop_SellActionPerformed

    private void btnNext_Product_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Stop_SellActionPerformed
        // TODO add your handling code here:
        minProduct_Stop_Sell = maxProduct_Stop_Sell + 1;
        maxProduct_Stop_Sell += 10;
        boolean checkList = checkNull_Table(this.pds.getNext_Product_Stop_selling(minProduct_Stop_Sell, maxProduct_Stop_Sell));
        if (checkList) {
            this.loadProduct_Stop();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_Stop_Sell -= 10;
            minProduct_Stop_Sell = maxProduct_Stop_Sell - 9;
            return;
        }

    }//GEN-LAST:event_btnNext_Product_Stop_SellActionPerformed

    private void btnRestore_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore_ProductActionPerformed
        if (idProduct_Stop_Sell.equalsIgnoreCase("")) {
            return;
        }
        if (this.pds.KhoiPhuc(idProduct_Stop_Sell)) {
            JOptionPane.showMessageDialog(this, "Khoi phuc thanh cong");
            //            product = new Product(f);
            this.loadProduct_Stop();
            this.load_Product();
//            this.loadcbbProduct();
//            this.load_Product_Extra();
        }
    }//GEN-LAST:event_btnRestore_ProductActionPerformed

    private void tblProduc_Stop_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduc_Stop_sellMouseClicked
        // TODO add your handling code here:
        int row = tblProduc_Stop_sell.getSelectedRow();
        if (row < 0) {
            return;
        }
        idProduct_Stop_Sell = tblProduc_Stop_sell.getValueAt(row, 0).toString();
    }//GEN-LAST:event_tblProduc_Stop_sellMouseClicked

    private void btnStop_Sell_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Sell_ProductActionPerformed
        if (txtName_Product.getText().trim().equalsIgnoreCase("")) {
            return;
        }
        if (this.pds.xoa(txtName_Product.getText().trim()) == true) {
            JOptionPane.showMessageDialog(this, "Da xoa thanh cong");
            this.load_Product_Detail();
            this.load_Product();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
            this.loadProduct_Stop();
            this.loadProduct_Deteail_Stop_Sell();
            this.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Da xoa that bai");
            return;
        }
    }//GEN-LAST:event_btnStop_Sell_ProductActionPerformed

    private void btnRestore_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        if (idProduct_Detail_Stop_Sell == null) {
            return;
        }
        if (pdds.khoiPhuc(idProduct_Detail_Stop_Sell)) {
            JOptionPane.showMessageDialog(this, "Da khoi phuc");
            this.loadProduct_Deteail_Stop_Sell();
            this.load_Product_Detail();
        }
    }//GEN-LAST:event_btnRestore_Product_Detail_Stop_SellActionPerformed

    private void btnNext_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell + 1;
        maxProduct_Detail_Stop_Sell += 10;
        boolean checkList = checkNull_Table(this.pdds.getRestore_Product_Detail_stop_selling(minProduct_Detail_Stop_Sell, maxProduct_Detail_Stop_Sell));
        if (checkList) {
            this.loadProduct_Deteail_Stop_Sell();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_Detail_Stop_Sell -= 10;
            minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Detail_Stop_SellActionPerformed

    private void btnPre_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        maxProduct_Detail_Stop_Sell -= 10;
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
        if (maxProduct_Detail_Stop_Sell < 1) {
            maxProduct_Detail_Stop_Sell = 10;
            minProduct_Detail_Stop_Sell = 1;
            return;
        }
        this.loadProduct_Deteail_Stop_Sell();
    }//GEN-LAST:event_btnPre_Product_Detail_Stop_SellActionPerformed

    private void tblProduct_Detail_Stop_SellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Detail_Stop_SellMouseClicked
        // TODO add your handling code here:
//        int row = tblProduct_Detail.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
//        idProduct_Detail_Stop_Sell = tblProduct_Detail.getValueAt(row, 0).toString();
    }//GEN-LAST:event_tblProduct_Detail_Stop_SellMouseClicked

    private void btnStop_Working_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Working_AttributeActionPerformed
        // TODO add your handling code here:
        if (idTT.equals("")) {
            return;
        }
        if (rdoSize.isSelected() == true) {
            if (this.ss.remove(idTT)) {
                JOptionPane.showMessageDialog(this, "Da Xoa size.");
                this.loadSize();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoColor.isSelected() == true) {
            if (this.cls.xoa(idTT)) {
                JOptionPane.showMessageDialog(this, "Da xoa mau");
                this.loadColer();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoMaterial.isSelected() == true) {
            if (this.mts.xoa(idTT)) {
                JOptionPane.showMessageDialog(this, "Da xoa vat lieu.");
                this.loadMaterial();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoThickness.isSelected() == true) {
            try {
                if (this.tns.xoa(idTT)) {
                    JOptionPane.showMessageDialog(this, "Da xoa do day.");
                    this.loadThickness();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (rdoCustom.isSelected() == true) {
            if (this.cts.xoa(idTT)) {
                JOptionPane.showMessageDialog(this, "Da them kieu.");
                this.loadCustom();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        }
    }//GEN-LAST:event_btnStop_Working_AttributeActionPerformed

    private void tblProduct_Has_No_Category_YetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Has_No_Category_YetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProduct_Has_No_Category_YetMouseClicked

    private void btnPre_Product_Has_No_Category_YetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Has_No_Category_YetActionPerformed
        // TODO add your handling code here:
        maxProduct_tab3 -= 10;
        minProduct_tab3 = maxProduct_tab3 - 9;
        if (minProduct_tab3 < 1) {
            maxProduct_tab3 = 10;
            minProduct_tab3 = 1;
            return;
        }
        this.load_Product();
    }//GEN-LAST:event_btnPre_Product_Has_No_Category_YetActionPerformed

    private void btnNext_Product_Has_No_Category_YetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Has_No_Category_YetActionPerformed
        // TODO add your handling code here:
        minProduct_tab1 = maxProduct_tab3 + 1;
        maxProduct_tab3 += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(minProduct_tab3, maxProduct_tab3));
        if (checkList) {
            this.load_Product();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_tab3 -= 10;
            minProduct_tab3 = maxProduct_tab3 - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Has_No_Category_YetActionPerformed

    private void btnAdd_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        ProductDetail prdt = formct();
        if (this.checkSPCTNB(txtName_Product.getText().trim(), cbbSize1.getSelectedItem().toString(), cbbColor1.getSelectedItem().toString()) == false) {
            return;
        }

        if (this.checkSPCTCB(txtName_Product.getText().trim(), cbbSize1.getSelectedItem().toString(), cbbColor1.getSelectedItem().toString()) == false) {
            return;
        }

        if (prdt == null) {
            return;
        }
        if (pdds.them(prdt) == true) {
            JOptionPane.showMessageDialog(this, "them thanh cong");
            this.load_Product_Detail();
        } else {
            JOptionPane.showMessageDialog(this, "them that bai");
            return;
        }
    }//GEN-LAST:event_btnAdd_Product_Detail1ActionPerformed

    private void btnFix_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        int row = tblProduct_Detail1.getSelectedRow();
        if (row < 0) {
            return;
        }
        ProductDetail spct = formct();
        if (this.pdds.sua(idProduct_Detail, spct) == true) {
            JOptionPane.showMessageDialog(this, "Da sua thanh cong");
            this.load_Product_Detail();
        } else {
            JOptionPane.showMessageDialog(this, "Sua that bai");
            return;
        }
    }//GEN-LAST:event_btnFix_Product_Detail1ActionPerformed

    private void btnStop_Sell_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Sell_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        int row = tblProduct_Detail1.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (this.pdds.xoa(idProduct_Detail) == true) {
            JOptionPane.showMessageDialog(this, "Da ngung ban");
            this.load_Product_Detail();
            this.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Da ngung ba that bai");
            return;
        }
    }//GEN-LAST:event_btnStop_Sell_Product_Detail1ActionPerformed

    private void btnClear_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_Product_Detail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClear_Product_Detail1ActionPerformed

    private void tblProduct_Detail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Detail1MouseClicked
        // TODO add your handling code here:
        int row = tblProduct_Detail1.getSelectedRow();
        if (row < 0) {
            return;
        }
        ProductDetail prd = this.pdds.getProductDetail_Selling_Next(idProduct_Extra, minProduct_detail, maxProduct_detail).get(row);
        idProduct_Detail = tblProduct_Detail1.getValueAt(row, 0).toString().trim();
        cbbColor1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 2).toString().trim());
        cbbSize1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 1).toString().trim());
        txtQuantity1.setText(tblProduct_Detail1.getValueAt(row, 3).toString().trim());
        //xuat anh
        byte[] retrievedImageData = prd.getImage();
        BufferedImage image = getImageFromByteArray(retrievedImageData);
        if (image != null) {
            lblImage_Detail.setText("");
            int width = lblImage_Detail.getWidth();
            int height = lblImage_Detail.getHeight();
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(width, height, 0));
            lblImage_Detail.setIcon(imageIcon);
        } else {
            lblImage_Detail.setIcon(null);
            lblImage_Detail.setText("Không thể hiển thị ảnh.");
        }
    }//GEN-LAST:event_tblProduct_Detail1MouseClicked

    private void btnPre_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        maxProduct_detail -= 10;
        minProduct_detail = maxProduct_detail - 9;
        if (minProduct_detail < 1) {
            maxProduct_detail = 10;
            minProduct_detail = 1;
            return;
        }
        this.load_Product_Detail();
    }//GEN-LAST:event_btnPre_Product_Detail1ActionPerformed

    private void btnNext_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        minProduct_detail = maxProduct_detail + 1;
        maxProduct_detail += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(minProduct_detail, maxProduct_detail));
        if (checkList) {
            this.load_Product_Detail();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_detail -= 10;
            minProduct_detail = maxProduct_detail - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Detail1ActionPerformed

    private void btnPre_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_AttributeActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadSize();
        } else if (rdoColor.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadColer();
        } else if (rdoMaterial.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadMaterial();
        } else if (rdoThickness.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadThickness();
        } else if (rdoCustom.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadCustom();
        }
    }//GEN-LAST:event_btnPre_AttributeActionPerformed

    private void btnNext_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_AttributeActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(minAttribute, maxAttribute));
            if (checkList) {
                this.loadSize();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoColor.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(minAttribute, maxAttribute));
            if (checkList) {
                this.loadColer();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoMaterial.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(minAttribute, maxAttribute));
            if (checkList) {
                this.loadMaterial();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoThickness.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(minAttribute, maxAttribute));
            if (checkList) {
                this.loadThickness();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoCustom.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(minAttribute, maxAttribute));
            if (checkList) {
                this.loadCustom();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        }
    }//GEN-LAST:event_btnNext_AttributeActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser fieChooser = new JFileChooser();
            int option = fieChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = fieChooser.getSelectedFile();
                Image img = ImageIO.read(file);
                String filePath = file.getAbsolutePath();
                int width = lblImage.getWidth();
                int height = lblImage.getHeight();
                lblImage.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
                nameImage_Product = filePath.trim();
                System.out.println("ten file;" + nameImage_Product);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void lblImage_DetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage_DetailMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser fieChooser = new JFileChooser();
            int option = fieChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = fieChooser.getSelectedFile();
                Image img = ImageIO.read(file);
                String filePath = file.getAbsolutePath();
                int width = lblImage_Detail.getWidth();
                int height = lblImage_Detail.getHeight();
                lblImage_Detail.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
                nameImage_Product_Detail = filePath.trim();
                System.out.println("ten file;" + nameImage_Product_Detail);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblImage_DetailMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTT;
    private javax.swing.JButton btnAdd_Attribute;
    private javax.swing.JButton btnAdd_Product;
    private javax.swing.JButton btnAdd_Product_Detail1;
    private javax.swing.JButton btnClear_Attribute;
    private javax.swing.JButton btnClear_Product;
    private javax.swing.JButton btnClear_Product_Detail1;
    private javax.swing.JButton btnFix_Attribute;
    private javax.swing.JButton btnFix_Produc;
    private javax.swing.JButton btnFix_Product_Detail1;
    private javax.swing.JButton btnNext_Attribute;
    private javax.swing.JButton btnNext_Product_Detail1;
    private javax.swing.JButton btnNext_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnNext_Product_Has_No_Category_Yet;
    private javax.swing.JButton btnNext_Product_Stop_Sell;
    private javax.swing.JButton btnNext__Product;
    private javax.swing.JButton btnPre_Attribute;
    private javax.swing.JButton btnPre_Product_Detail1;
    private javax.swing.JButton btnPre_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnPre_Product_Has_No_Category_Yet;
    private javax.swing.JButton btnPre_Product_Stop_Sell;
    private javax.swing.JButton btnPre__Product;
    private javax.swing.JButton btnRestore_Product;
    private javax.swing.JButton btnRestore_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnStop_Sell_Product;
    private javax.swing.JButton btnStop_Sell_Product_Detail1;
    private javax.swing.JButton btnStop_Working_Attribute;
    private javax.swing.JComboBox<String> cbbColor1;
    private javax.swing.JComboBox<String> cbbCustom;
    private javax.swing.JComboBox<String> cbbMaterial;
    private javax.swing.JComboBox<String> cbbSize1;
    private javax.swing.JComboBox<String> cbbThickness;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImage_Detail;
    private javax.swing.JPanel pnlProduct_Detail;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoCustom;
    private javax.swing.JRadioButton rdoMaterial;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JRadioButton rdoThickness;
    private javax.swing.JTable tblAttribute;
    private javax.swing.JTable tblProduc_Stop_sell;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProduct_Detail1;
    private javax.swing.JTable tblProduct_Detail_Stop_Sell;
    private javax.swing.JTable tblProduct_Has_No_Category_Yet;
    private javax.swing.JTextArea txtDescribe;
    private javax.swing.JTextField txtName_Attribute;
    private javax.swing.JTextField txtName_Product;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity1;
    // End of variables declaration//GEN-END:variables
}
