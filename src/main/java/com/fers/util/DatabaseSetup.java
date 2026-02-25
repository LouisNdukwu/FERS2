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
public class DatabaseSetup {
    public static void initialize() {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY,
                    name TEXT,
                    status TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS incidents (
                    incident_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT NOT NULL,
                    start_time TEXT NOT NULL,
                    active INTEGER NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS checkins (
                    userId INTEGER,
                    incidentId INTEGER,
                    status TEXT,
                    timestamp TEXT
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
