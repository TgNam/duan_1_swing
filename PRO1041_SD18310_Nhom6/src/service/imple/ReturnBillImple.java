/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import model.entity.ReturnBill;
import repository.Return_Bill_Repository;
import service.ReturnBillService;

/**
 *
 * @author lenovo
 */
public class ReturnBillImple implements ReturnBillService {

    private Return_Bill_Repository returnBillRepository = new Return_Bill_Repository();

    @Override
    public boolean insert(ReturnBill returnBill) {
        return returnBillRepository.insert(returnBill);
    }
}
