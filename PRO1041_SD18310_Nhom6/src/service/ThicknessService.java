/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.Thickness;

/**
 *
 * @author thiet
 */
public interface ThicknessService {
    ArrayList<Thickness> getAll();
    public boolean them(Thickness tk);
    public boolean sua(String id, Thickness tk);
     //them vao 1/12
    public boolean xoa(String id);
}
