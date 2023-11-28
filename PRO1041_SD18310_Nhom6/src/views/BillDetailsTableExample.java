/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class BillDetailsTableExample {
    private DefaultTableModel tableModel;
    private JTable tblBillDetails;

    public BillDetailsTableExample() {
        tblBillDetails = new JTable();
        column();
        addSampleData();

        tblBillDetails.setDefaultEditor(Object.class, null);

        TableColumnModel columnModel = tblBillDetails.getColumnModel();
        columnModel.getColumn(columnModel.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });

        columnModel.getColumn(columnModel.getColumnCount() - 1).setCellEditor(tblBillDetails.getDefaultEditor(Boolean.class));
    }

    public void column() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == getColumnCount() - 1;
            }
        };

        String[] column = {"STT", "Tên Sản Phẩm", "Màu", "Size", "Số Lượng", "Đơn Giá", ""};
        tableModel.setColumnIdentifiers(column);
        tblBillDetails.setModel(tableModel);
    }
    public void addSampleData() {
        // Dữ liệu mẫu
        Object[][] sampleData = {
                {1, "Sản phẩm 1", "Đỏ", "M", 5, 200000, false},
                {2, "Sản phẩm 2", "Xanh", "L", 3, 150000, false},
                {3, "Sản phẩm 3", "Vàng", "S", 2, 180000, false}
                // Thêm dữ liệu mẫu khác nếu cần
        };

        // Xóa tất cả các dòng hiện tại trong bảng
        tableModel.setRowCount(0);

        // Thêm dữ liệu mẫu vào bảng
        for (Object[] data : sampleData) {
            tableModel.addRow(data);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bill Details Table Example");
            BillDetailsTableExample example = new BillDetailsTableExample();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(example.tblBillDetails));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}

