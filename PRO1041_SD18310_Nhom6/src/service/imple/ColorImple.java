/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.Color;
import repository.ColorRepository;
import service.ColorService;

/**
 *
 * @author thiet
 */
public class ColorImple implements ColorService{
    ColorRepository cl = new ColorRepository();
    @Override
    public ArrayList<Color> getAll() {
        return cl.getListCL();
    }

    @Override
    public boolean them(Color c) {
        if(cl.Insert(c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean sua(String id, Color c) {
        if(cl.Update(id, c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if(cl.Delete(id)){
            return true;
        }else{
            return false;
        }
    }
    
}
