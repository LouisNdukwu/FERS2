/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.service;
import com.fers.model.Incident;
import com.fers.db.DatabaseManager;
import java.sql.*;
import java.time.LocalDateTime;

/**
 *
 * @author ndukw
 */
public class IncidentService {
    private  Incident activeIncident;
    
    public void createIncident(Incident incident){
        this.activeIncident = incident;
    }
    public Incident getActiveIncident(){
        return activeIncident;
    }
    public void closeIncident(){
        if (activeIncident != null){
            activeIncident.closeIncident();
            activeIncident = null;
        }
    }
    public boolean hasActiveIncident(){
        return activeIncident != null;
    }
    public void createIncidentInDatabase(String type){
        String sql = """
            INSERT INTO incidents (type, start_time, active)
            VALUES (?, ?, 1);
        """;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = 
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, type);
            pstmt.setString(2, LocalDateTime.now().toString());
            pstmt.executeUpdate();
            
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()){
                int incidentId = keys.getInt(1);
                this.activeIncident = new Incident(incidentId, type);
            }
            System.out.println("Incident created and stored.");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void closeIncidentInDatabse(int incidentId){
        String sql = "UPDATE incidents SET active = 0 WHERE incident_id = ?";
        
       try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
           pstmt.setInt(1, incidentId);
           pstmt.executeUpdate();
           
           System.out.println("Incident closed in databse.");
       } catch (SQLException e){
           e.printStackTrace();
       }
    }
}
