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
public class Incident {
    private int incidentId;
    private String type;
    private LocalDateTime startTime;
    private boolean active;
    
    public Incident(int incidentId, String type){
        this.incidentId = incidentId;
        this.type = type;
        this.startTime = LocalDateTime.now();
        this.active = true;
    }
    public int getIncidentId(){
        return incidentId;
    }
    public String getType(){
        return type;
    }
    public LocalDateTime getStartTime(){
        return startTime;
    }
    public boolean isActive(){
        return active;
    }
    public void closeIncident(){
        this.active = false;
    }
    @Override
    public String toString(){
        String status = active ? "ACTIVE" : "CLOSED";
         return "Incident{" + "incidentId=" + incidentId + ", type='" + type + '\'' + ", startTime=" + startTime + ", active=" + active + '}';
    }
}
