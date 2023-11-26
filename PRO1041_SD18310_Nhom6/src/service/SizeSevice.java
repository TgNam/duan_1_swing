/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.Size;

/**
 *
 * @author thiet
 */
public interface SizeSevice {
    ArrayList<Size> getAll();
    public boolean Insert(Size sz);
    public boolean Update(String id, Size sz);
}
