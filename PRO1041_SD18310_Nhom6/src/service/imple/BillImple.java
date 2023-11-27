/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.Bill;
import repository.BillRepository;
import service.BillService;

/**
 *
 * @author TgNam
 */
public class BillImple implements BillService {

    BillRepository br = new BillRepository();

    @Override
    public ArrayList<Bill> getListBill_0() {
        return br.getBill_0();
    }

    @Override
    public String add_bill(Bill b) {
        if (br.add_bill(b)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại!";
        }
    }

    //lk vs inter
    @Override
    public ArrayList<Bill> getAll() {
        return br.getBil_All();
    }

    //Xóa bill thông qua id
    @Override
    public boolean delete_bill_id(Bill b) {
        if (br.delete_bill_id(b)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Bill getById(Long id) {
        return br.getById(id);
    }

}
