package service.imple;

import repository.ReturnBill_Detail_Repository;
import service.ReturnBillDetail;

/**
 *
 * @author lenovo
 */
public class ReturnBillDetailImple implements ReturnBillDetail {

    
    private ReturnBill_Detail_Repository returnBillDetailRepository = new ReturnBill_Detail_Repository();

    @Override
    public boolean insert(model.entity.ReturnBillDetail returnBillDetail) {
        return returnBillDetailRepository.insert(returnBillDetail);
    }
}
