package com.fers.service;

import com.fers.db.CheckInDAO;
import com.fers.model.CheckIn;
import com.fers.model.User;

import java.util.List;

/**
 * Service layer for handling check-in business logic.
 * Acts as an intermediary between the UI and DAO layers.
 */
public class CheckInService {

    /**
     * Checks a user into an incident with a given status.
     *
     * @param user the user performing the check-in
     * @param incidentId the incident ID
     * @param status the user's status (SAFE, INJURED, MISSING, etc.)
     * @return confirmation message indicating success or failure
     */
    public String checkInUser(User user, int incidentId, User.Status status) {

        CheckIn checkIn = new CheckIn(user.getId(), incidentId, status);

        boolean success = CheckInDAO.insertCheckIn(checkIn);

        if (!success) {
            return "User has already checked in for this incident.";
        }

        user.setStatus(status);
        return "Check-in recorded successfully.";
    }

    /**
     * Retrieves all check-ins associated with a specific incident.
     *
     * @param incidentId the incident ID
     * @return list of CheckIn records
     */
    public List<CheckIn> getCheckInsForIncident(int incidentId) {
        return CheckInDAO.getCheckInsByIncident(incidentId);
    }
}