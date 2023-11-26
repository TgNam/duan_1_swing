/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entity.User;

/**
 *
 * @author TgNam
 */
public interface UserService {
    ArrayList<User> getUser_name_phone();
    String add_user(User user);
    boolean add_user_all(User user, Date nowDate, String address);
    boolean Update_user_all(User user, String id);
    boolean Update_status_user(String status, User user) ;
    List<User> getCustomer();
}
