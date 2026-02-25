/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.service;
import com.fers.model.CheckIn;
import com.fers.model.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ndukw
 */
public class CheckInService {
    private List<CheckIn> checkIns = new ArrayList<>();
    
    public void checkInUSer(User user, int incidentId, User.Status status){
        user.setStatus(status);
        CheckIn checkIn = new CheckIn(user.getId(), incidentId, status);
        checkIns.add(checkIn);
    }
    public List<CheckIn> getAllCheckIns(){
        return checkIns;
    }
}
