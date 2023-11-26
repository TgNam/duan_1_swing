/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import model.entity.Color;
/**
 *
 * @author thiet
 */
public class ColorRepository {

     public ArrayList<Color> getListCL() {
        ArrayList<Color> List = new ArrayList<>();
        try {
            String sql = "select db_levents.color.id, db_levents.color.name_color, db_levents.color.created_at, db_levents.color.updated_at  from db_levents.color;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Color cl;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                cl = new Color( created_at, id, updated_at, name);
                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
     public boolean  Insert(Color cl){
        try {
            String sql = "INSERT INTO `db_levents`.`color` (`created_at`, `updated_at`, `name_color`) VALUES (curdate(),curdate(),?);";
            JDBCHelped.excuteUpdate(sql, cl.getNameColor());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
      public boolean  Update(String id,Color cl){
        try {
            String sql = "UPDATE `db_levents`.`color` SET `updated_at` = curdate(), `name_color` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, cl.getNameColor(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
