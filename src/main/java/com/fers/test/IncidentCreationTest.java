package com.fers.test;

import com.fers.service.IncidentService;

public class IncidentCreationTest {

    public static void main(String[] args) {
        IncidentService service = new IncidentService();

        service.createIncident("Fire");

        if (service.hasActiveIncident()) {
            System.out.println("✅ Incident created:");
            System.out.println(service.getActiveIncident());
        } else {
            System.out.println("❌ Incident creation failed");
        }
    }
}