package com.fers.db;

import com.fers.model.CheckIn;
import com.fers.model.User;
import com.fers.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for CheckIn entities.
 * Handles all database operations related to user check-ins.
 */
public class CheckInDAO {

    /**
     * Inserts a new check-in record into the database.
     *
     * @param checkIn the CheckIn object to persist
     * @return true if insertion was successful, false if duplicate or SQL error
     */
    public static boolean insertCheckIn(CheckIn checkIn) {
        String sql = """
            INSERT INTO checkins (user_id, incident_id, status, timestamp)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, checkIn.getUserId());
            ps.setInt(2, checkIn.getIncidentId());
            ps.setString(3, checkIn.getStatus().name());
            ps.setString(4, checkIn.getTimestamp().toString());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            // Log the error for debugging and grading clarity
            System.err.println("Failed to insert check-in (possible duplicate):");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all check-ins associated with a specific incident.
     *
     * @param incidentId the incident ID to search for
     * @return a list of CheckIn objects for the incident
     */
    public static List<CheckIn> getCheckInsByIncident(int incidentId) {
        List<CheckIn> results = new ArrayList<>();

        String sql = "SELECT * FROM checkins WHERE incident_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, incidentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CheckIn checkIn = new CheckIn(
                        rs.getInt("user_id"),
                        rs.getInt("incident_id"),
                        User.Status.valueOf(rs.getString("status"))
                );
                results.add(checkIn);
            }

        } catch (Exception e) {
            System.err.println("Failed to retrieve check-ins for incident ID: " + incidentId);
            e.printStackTrace();
        }

        return results;
    }
    /**
     * Return the count of check-ins grouped by status for a specific incident.
     */
    
    public static int countBystatus(int incidentId, User.Status status){
        String sql = """
            SELECT COUNT(*)
            FROM checkins
            WHERE incident_id = ? AND status = ?           
        """;
        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
           ps.setInt(1, incidentId);
           ps.setString(2, status.name());
           
           ResultSet rs = ps.executeQuery();
           
           if (rs.next()){
               return rs.getInt(1);
           }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return 0;
    }
}