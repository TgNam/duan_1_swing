/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.entity.ReturnBill;

/**
 *
 * @author thiet
 */
public class Return_Bill_Repository {
    public boolean Insert(ReturnBill rb){
        try {
            String sql = """
                         INSERT INTO return_bill (created_at, total_cost, bill_id, reason_description)
                         VALUES 
                         ('2023-11-04 10:00:00', '320000', '1','Hàng lỗi');
                         """;
            JDBCHelped.excuteUpdate(sql, rb.getCreatedAt(), rb.getBillId().getId(), rb.getReasonDescription());
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
