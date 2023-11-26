/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.entity.Color;
import model.entity.Size;

/**
 *
 * @author TgNam
 */
public class SizeRepository {
    public ArrayList<Size> getListSize() {
        ArrayList<Size> List = new ArrayList<>();
        try {
            String sql = "select db_levents.size.id, db_levents.size.name_size, db_levents.size.created_at, db_levents.size.updated_at from db_levents.size;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Size s;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                s = new Size(created_at, id, updated_at, name);
                List.add(s);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public boolean Insert(Size s){
        try {
            String sql = "INSERT INTO `db_levents`.`size` (`created_at`,`updated_at`, `name_size`) VALUES (curdate(),curdate(),?);";
            JDBCHelped.excuteUpdate(sql, s.getNameSize());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Update(String id,Size s){
        try {
            String sql = "UPDATE `db_levents`.`size` SET `updated_at` = curdate(), `name_size` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, s.getNameSize(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
