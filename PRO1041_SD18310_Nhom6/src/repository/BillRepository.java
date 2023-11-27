/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.entity.Bill;
import java.sql.*;
import java.util.List;
import model.entity.Address;
import model.entity.User;
import model.entity.Voucher;

/**
 *
 * @author TgNam
 */
public class BillRepository {

    //select lấy dữ liệu hóa đơn chưa thanh toán
    public ArrayList<Bill> getBill_0() {
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "  bill.created_at,\n"
                    + "  bill.id,\n"
                    + "  user.full_name,\n"
                    + "  user.number_phone,\n"
                    + "  bill.status\n"
                    + "FROM db_levents.bill\n"
                    + "join db_levents.user on user.id = bill.user_id where bill.status = 0;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new Bill(rs.getDate(1), rs.getString(2), new User(rs.getString(3), rs.getString(4)), rs.getString(5))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add_bill(Bill b) {
        try {
            String sql = "INSERT INTO db_levents.bill (created_at, updated_at, delivery_date, total_cost, into_money, status, user_id,voucher_id,address_id)\n"
                    + "VALUES \n"
                    + "(?, ?, ?, null, null, 0, (SELECT id FROM db_levents.user where number_phone = ?), null, null);";
            JDBCHelped.excuteUpdate(sql, b.getCreatedAt(), b.getUpdatedAt(), b.getDeliveryDate(), b.getUserId().getNumberPhone());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Them thang getBill_all
    public ArrayList<Bill> getBil_All() {
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    db_levents.bill.id,\n"
                    + "    db_levents.user.full_name,\n"
                    + "    db_levents.user.number_phone,\n"
                    + "    db_levents.bill.into_money,\n"
                    + "    db_levents.bill.total_cost,\n"
                    + "    db_levents.address.address_detail,\n"
                    + "    db_levents.bill.created_at,\n"
                    + "    db_levents.bill.delivery_date,\n"
                    + "    db_levents.bill.updated_at,\n"
                    + "    db_levents.bill.voucher_id,\n"
                    + "    db_levents.bill.status,\n"
                    + "    db_levents.user.id\n"
                    + "FROM\n"
                    + "    db_levents.bill\n"
                    + "INNER JOIN\n"
                    + "    db_levents.user ON db_levents.bill.user_id = db_levents.user.id\n"
                    + "INNER JOIN\n"
                    + "    db_levents.address ON db_levents.bill.address_id = db_levents.address.id\n"
                    + "LEFT JOIN\n"
                    + "    db_levents.user_role ON db_levents.user.id = db_levents.user_role.user_id\n"
                    + "INNER JOIN\n"
                    + "    db_levents.role ON db_levents.user_role.role_id = db_levents.role.id\n"
                    + "WHERE\n"
                    + "    db_levents.bill.status = '1';";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String number_Phone = rs.getString(3);
                BigDecimal into_money = rs.getBigDecimal(4);
                BigDecimal total_cost = rs.getBigDecimal(5);
                String address_detail = rs.getString(6);
                Date created_at = rs.getDate(7);
                Date delivery_date = rs.getDate(8);
                Date updated_at = rs.getDate(9);
                String voucher = rs.getString(10);
                String status = rs.getString(11);
                String idUser = rs.getString(12);
                Bill b;
                b = new Bill(into_money, total_cost, new Address(address_detail), created_at, delivery_date, id, updated_at, new User(idUser, name, number_Phone), new Voucher(voucher), status);
                list.add(b);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Xóa bill thông qua id
    public boolean delete_bill_id(Bill b) {
        try {
            String sql = "Delete FROM db_levents.bill where id = ?;";
            JDBCHelped.excuteUpdate(sql, b.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // anh linh đã ghé thăm chỗ này
    public Bill getById(Long idBill) {
        String query = "SELECT * FROM bill WHERE id = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, idBill);
            if (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String number_Phone = rs.getString(3);
                BigDecimal into_money = rs.getBigDecimal(4);
                BigDecimal total_cost = rs.getBigDecimal(5);
                String address_detail = rs.getString(6);
                Date created_at = rs.getDate(7);
                Date delivery_date = rs.getDate(8);
                Date updated_at = rs.getDate(9);
                String voucher = rs.getString(10);
                String status = rs.getString(11);
                String idUser = rs.getString(12);

                Address address = new Address(address_detail);
                User user = new User(idUser, name, number_Phone);
                Voucher voucherObj = new Voucher(voucher);

                Bill bill = new Bill(into_money, total_cost, address, created_at, delivery_date, id, updated_at, user, voucherObj, status);

                return bill;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // bye----------
}
