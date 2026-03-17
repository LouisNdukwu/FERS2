package com.fers.tests;

import com.fers.db.CheckInDAO;
import com.fers.db.IncidentDAO;
import com.fers.model.CheckIn;
import com.fers.model.Incident;
import com.fers.model.User;
import com.fers.util.DatabaseInitializer;
import com.fers.util.DatabaseUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckInDAOTest {

    @BeforeAll
    static void setup() {
        DatabaseUtil.enableTestMode();
        DatabaseInitializer.initialize();
    }

    @Test
    public void testInsertCheckIn() {

        // Create a new incident first
        Incident incident = IncidentDAO.createIncident("Test Incident");

        // Create test user
        User user = new User(999, "TestUser");

        // Create check-in
        CheckIn checkIn = new CheckIn(user.getId(), incident.getIncidentId(), User.Status.SAFE);

        boolean result = CheckInDAO.insertCheckIn(checkIn);

        assertTrue(result);
    }
}