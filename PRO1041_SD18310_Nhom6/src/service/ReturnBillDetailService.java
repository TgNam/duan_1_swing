/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.entity.ReturnBillDetail;

/**
 *
 * @author lenovo
 */
public interface ReturnBillDetailService {
    boolean insert(model.entity.ReturnBillDetail returnBillDetail);
    public List<ReturnBillDetail> getAll();
    public List<ReturnBillDetail> getByIdBill(String idBill);
}
