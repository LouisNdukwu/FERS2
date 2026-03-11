package com.fers.db;

import com.fers.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void main(String[] args) {
        String createIncidentsTable = """
            CREATE TABLE IF NOT EXISTS incidents (
                incident_id INTEGER PRIMARY KEY AUTOINCREMENT,
                type TEXT NOT NULL,
                start_time TEXT NOT NULL,
                active INTEGER NOT NULL
            );
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createIncidentsTable);
            System.out.println("✅ incidents table created (or already exists)");

        } catch (Exception e) {
            System.out.println("❌ Failed to create incidents table");
            e.printStackTrace();
        }
    }
}