/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.entity.ExchangeBill;
import service.ExchangeService;
import java.sql.*;
import model.entity.Bill;

/**
 *
 * @author thiet
 */
public class ExchangeRepository {

    public ArrayList<ExchangeBill> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean Insert(ExchangeBill ex) {
        try {
            String sql = "INSERT INTO exchange_bill (created_at, bill_id, describe_reason)\n"
                    + "VALUES \n"
                    + "(?, ?,?);";
            JDBCHelped.excuteUpdate(sql, ex.getCreatedAt(), ex.getBillId().getId(), ex.getDescribeReason());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // load table 26/11/2023
    public ArrayList<ExchangeBill> getExchangeBill(){
        ArrayList<ExchangeBill> list = new ArrayList<>();
        try {
            String sql = "select id, bill_id, created_at, updated_at, describe_reason from exchange_bill;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {                
                String id = rs.getString(1);
                String idBill = rs.getString(2);
                Date created = rs.getDate(3);
                Date updated = rs.getDate(4);
                String describe_reason = rs.getString(5);
                Bill b = new Bill(idBill);
                ExchangeBill exb = new ExchangeBill(b, created, id, updated, describe_reason);
                list.add(exb);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
