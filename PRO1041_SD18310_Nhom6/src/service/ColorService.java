/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.Color;

/**
 *
 * @author thiet
 */
public interface ColorService {
    ArrayList<Color> getAll();
    public boolean them(Color c);
    public boolean  sua(String id, Color c);
     //them vao 1/12
    public boolean xoa(String id);
}
