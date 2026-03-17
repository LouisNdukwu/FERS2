package com.fers.util;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Initializes the SQLite database by creating required tables
 * if they do not already exist.
 *
 * Tables created:
 * - users
 * - incidents
 * - checkins
 */
public class DatabaseInitializer {
    /**
     * Creates all necessary database tables for the FERS system.
     */

    public static void initialize() {

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // USERS TABLE
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    user_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    status TEXT
                )
            """);

            // INCIDENTS TABLE
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS incidents (
                    incident_id INTEGER PRIMARY KEY,
                    type TEXT NOT NULL,
                    start_time TEXT NOT NULL,
                    active INTEGER NOT NULL
                )
            """);

            // CHECK-INS
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS checkins (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    incident_id INTEGER NOT NULL,
                    status TEXT NOT NULL,
                    timestamp TEXT NOT NULL,
                    UNIQUE(user_id, incident_id),
                    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id),
                    FOREIGN KEY (user_id) REFERENCES users(user_id)
                )
            """);

            System.out.println("Database tables initialized successfully.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database tables", e);
        }
    }
}