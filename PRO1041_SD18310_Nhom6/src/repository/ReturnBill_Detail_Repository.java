/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.entity.ReturnBillDetail;

/**
 *
 * @author thiet
 */
public class ReturnBill_Detail_Repository {
    
    //start linh dz
    public boolean insert(ReturnBillDetail returnBillDetail) {
        String query = "INSERT INTO return_bill_detail(price_at_the_time_of_purchase, quantity_of_products_returned,"
                + " created_at, product_detail_id,return_bill_id,status) VALUES (?,?,NOW(),?,?,?) ";
        try {
            JDBCHelped.excuteUpdate(query, returnBillDetail.getPriceAtTheTimeOfPurchase(),
                    returnBillDetail.getQuantityOfProductsReturned(),returnBillDetail.getProductDetailId().getId(),
                    returnBillDetail.getReturnBillId().getId(), returnBillDetail.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    // end linh dz
}
