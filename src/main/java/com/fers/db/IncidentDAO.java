package com.fers.db;

import com.fers.model.Incident;
import com.fers.util.DatabaseUtil;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class IncidentDAO {
    public static Incident createIncident(String type) {

    // 🔒 Enforce single active incident
    closeActiveIncident();

    String sql = """
        INSERT INTO incidents (type, start_time, active)
        VALUES (?, datetime('now'), 1);
    """;

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, type);
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            return new Incident(id, type);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
    }
    
    public static Incident getActiveIncident() {
    String sql = "SELECT * FROM incidents WHERE active = 1 LIMIT 1";

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("type")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
    }
    
    public static void closeActiveIncident() {
        String sql = "UPDATE incidents SET active = 0 WHERE active = 1";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (Exception e) {
        e.printStackTrace();
        }
    
    }
    /**
 * Retrieves all incidents from the database.
 */
    public static List<Incident> getAllIncidents() {

        List<Incident> incidents = new ArrayList<>();

        String sql = "SELECT * FROM incidents";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            Incident incident = new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("type")
            );
            // New: set active status from DB
            boolean isActive = rs.getInt("active") == 1;
            if(!isActive){
                incident.closeIncident(); // mark it as closed in object
            }
            incidents.add(incident);
        }

        } catch (Exception e) {
        e.printStackTrace();
        }

        return incidents;
    }
    /**
 * Marks an incident as inactive.
 */
    public static boolean closeIncident(int incidentId) {

        String sql = "UPDATE incidents SET active = 0 WHERE incident_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, incidentId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
        e.printStackTrace();
        }

        return false;
    }
    
    /**
     * Retrieves an incident by ID
     */
    public static Incident getIncidentById(int incidentId){
        String sql = "SELECT * FROM incidents WHERE incident_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, incidentId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Incident incident = new Incident(
                        rs.getInt("incddident_id"),
                        rs.getString("type")
                );
                
                boolean isActive = rs.getInt("active")== 1;
                if(isActive){
                    incident.closeIncident();
                }
                return incident;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}