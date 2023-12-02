/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.Custom;
import repository.CustomRepository;
import service.CustomService;

/**
 *
 * @author thiet
 */
public class CustomImple implements CustomService{
    CustomRepository ct = new CustomRepository();
    @Override
    public ArrayList<Custom> getAll() {
        return ct.getListCustom();
    }

    @Override
    public boolean them(Custom c) {
        if(ct.Insert(c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean sua(String id, Custom c) {
        if(ct.Update(id, c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if(ct.Delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Custom> getCustom_Sell(int min, int max) {
        return ct.getCustom_Sell(min, max);
    }
    
}
