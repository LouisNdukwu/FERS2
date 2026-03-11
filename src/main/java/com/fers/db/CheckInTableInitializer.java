package com.fers.db;

import com.fers.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.Statement;

public class CheckInTableInitializer {

    public static void main(String[] args) {
        String sql = """
            CREATE TABLE IF NOT EXISTS checkins (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                incident_id INTEGER NOT NULL,
                status TEXT NOT NULL,
                checkin_time TEXT NOT NULL
            );
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("✅ checkins table ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}