/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.Size;
import repository.SizeRepository;
import service.SizeSevice;

/**
 *
 * @author thiet
 */
public class SizeImple implements SizeSevice{
    SizeRepository s = new SizeRepository();
    @Override
    public ArrayList<Size> getAll() {
        return s.getListSize();
    }

    @Override
    public boolean Insert(Size sz) {
        if(s.Insert(sz)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean Update(String id, Size sz) {
        if(s.Update(id, sz)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean remove(String id) {
        if(s.Delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Size> getSize_Sell(int min, int max) {
        return s.getSize_Sell(min, max);
    }
    
}
