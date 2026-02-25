/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.util;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author ndukw
 */
public class DatabaseInitializer {
    public static void initialize() {

        String createIncidentTable = """
            CREATE TABLE IF NOT EXISTS incidents (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                type TEXT NOT NULL,
                location TEXT NOT NULL,
                severity INTEGER NOT NULL,
                timestamp TEXT NOT NULL
            );
        """;

        String createCheckInTable = """
            CREATE TABLE IF NOT EXISTS checkins (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                userId TEXT NOT NULL,
                incidentId INTEGER NOT NULL,
                status TEXT NOT NULL,
                timestamp TEXT NOT NULL,
                FOREIGN KEY (incidentId) REFERENCES incidents(id)
            );
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createIncidentTable);
            stmt.execute(createCheckInTable);

            System.out.println("Database tables initialized successfully.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database tables", e);
        }
    }
}
