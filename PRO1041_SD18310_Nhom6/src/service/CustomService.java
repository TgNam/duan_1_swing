/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.entity.Custom;

/**
 *
 * @author thiet
 */
public interface CustomService {
    ArrayList<Custom> getAll();
    public boolean them(Custom c);
    public boolean sua(String id, Custom c);
     //them vao 1/12
    public boolean xoa(String id);
}
