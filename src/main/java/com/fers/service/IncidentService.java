package com.fers.service;

import com.fers.db.IncidentDAO;
import com.fers.model.Incident;

public class IncidentService {

    public Incident createIncident(String type) {
        return IncidentDAO.createIncident(type);
    }

    public Incident getActiveIncident() {
        return IncidentDAO.getActiveIncident();
    }

    public boolean hasActiveIncident() {
        return getActiveIncident() != null;
    }

    public void closeIncident() {
        IncidentDAO.closeActiveIncident();
    }
}