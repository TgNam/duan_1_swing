package service;

import java.util.List;
import model.entity.Voucher;

/**
 *
 * @author lenovo
 */
public interface VoucherService {
    List<Voucher> getAll();
    Voucher getById(String id);
    boolean insert(Voucher vc);
    boolean update(Voucher vc);
    boolean delete(String id);
    boolean resetStatus();
}
