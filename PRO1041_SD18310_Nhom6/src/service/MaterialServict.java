/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.Material;

/**
 *
 * @author thiet
 */
public interface MaterialServict {
    ArrayList<Material> getAll();
    public boolean them(Material mr);
    public boolean sua(String id, Material mr);
     //them vao 1/12
    public boolean xoa(String id);
    ArrayList<Material> getMaterial_Sell(int min, int max);
}
