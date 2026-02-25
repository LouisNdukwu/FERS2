/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.model;
import java.time.LocalDateTime;
/**
 *
 * @author ndukw
 */
public class CheckIn {
    private int userId;
    private int incidentId;
    private User.Status status;
    private LocalDateTime timestamp;
    
    public CheckIn(int userId, int incidentId, User.Status status){
        this.userId = userId;
        this.incidentId = incidentId;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
    private int getUserId(){
        return userId;
    }
    private int getIncidentId(){
        return incidentId;
    }
    private User.Status getStatus(){
        return status;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    @Override
    public String toString(){
        return "CheckIn{" + "userId=" + userId + ", incidentId=" + incidentId + ", status" + status + ", timestamp=" + timestamp + '}';
    }
}
