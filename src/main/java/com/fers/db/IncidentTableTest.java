package com.fers.db;

import com.fers.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class IncidentTableTest {

    public static void main(String[] args) {
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='incidents';"
            )
        ) {
            if (rs.next()) {
                System.out.println("✅ incidents table exists");
            } else {
                System.out.println("❌ incidents table NOT found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}