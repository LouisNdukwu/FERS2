/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.tests;

import com.fers.db.IncidentDAO;
import com.fers.model.Incident;
import com.fers.util.DatabaseUtil;
import com.fers.util.DatabaseInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author ndukw
 */
public class IncidentDAOTest {
    
    @BeforeAll
    static void setup(){
        DatabaseUtil.enableTestMode();
        DatabaseInitializer.initialize();
    }
    
    @Test
    public void testCreateIncident(){
        Incident incident = IncidentDAO.createIncident("Fire");
        
        assertNotNull(incident);
        assertEquals("Fire", incident.getType());
    }
}
