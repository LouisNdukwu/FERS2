/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.service;
import com.fers.model.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ndukw
 */
public class UserService {
    private List<User> users = new ArrayList<>();
    
    public void addUser(User user){
        users.add(user);
    }
    public User getUserById(int id){
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public List<User> getAllUsers(){
        return users;
    }
}
