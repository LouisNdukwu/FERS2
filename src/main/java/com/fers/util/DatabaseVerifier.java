/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement; 
/**
 *
 * @author ndukw
 */
public class DatabaseVerifier {
    public static void verifyTables() {
        String query = "SELECT name FROM sqlite_master WHERE type='table';";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Tables found in database:");

            while (rs.next()) {
                System.out.println("- " + rs.getString("name"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Database verification failed", e);
        }
    }
}
