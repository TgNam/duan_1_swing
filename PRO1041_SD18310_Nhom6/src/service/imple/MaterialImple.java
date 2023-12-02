/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.Material;
import repository.MaterialRepository;
import service.MaterialServict;

/**
 *
 * @author thiet
 */
public class MaterialImple implements MaterialServict{
    MaterialRepository m = new MaterialRepository();
    @Override
    public ArrayList<Material> getAll() {
        return m.getListNL();
    }

    @Override
    public boolean them(Material mr) {
        if(m.Insert(mr)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean sua(String id, Material mr) {
        if(m.Update(id, mr)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if(m.Delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Material> getMaterial_Sell(int min, int max) {
        return m.getMaterial_Sell(min, max);
    }
    
}
