package service;

import model.entity.ReturnBill;

/**
 *
 * @author lenovo
 */
public interface ReturnBillService {
    boolean insert(ReturnBill id);
    ReturnBill getByIdBill(String idBill);
    ReturnBill getById(String id);
}
