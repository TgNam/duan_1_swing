/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.ExchangeBill;

/**
 *
 * @author thiet
 */
public interface ExchangeService {
    public ArrayList<ExchangeBill> getList();
    public boolean Insert(ExchangeBill ex);
    //them 26/11
    public ArrayList<ExchangeBill> getExchangeBills();
}
