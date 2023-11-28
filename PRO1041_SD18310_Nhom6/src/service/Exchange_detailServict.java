/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.ExchangeBillDetail;

/**
 *
 * @author thiet
 */
public interface Exchange_detailServict {
    public ArrayList<ExchangeBillDetail> list();
    public boolean insert(ExchangeBillDetail ex);
    //26/11
    public ArrayList<ExchangeBillDetail> getExchangeBillDetail(String id);
    //them vao ngay 27//11
    public boolean getMinus_Product_Detail(String id, int quantity);
}
