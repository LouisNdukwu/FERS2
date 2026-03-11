package com.fers.db;

import com.fers.util.DatabaseUtil;
import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            System.out.println("✅ Database connection successful!");
            System.out.println("DB URL: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            System.out.println("❌ Database connection failed");
            e.printStackTrace();
        }
    }
}