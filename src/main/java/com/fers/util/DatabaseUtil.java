/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.util;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ndukw
 */
public class DatabaseUtil {
     private static final String DB_PATH =
            Paths.get("data", "fers.db").toAbsolutePath().toString();

    private static final String DB_URL =
            "jdbc:sqlite:" + DB_PATH;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database at: " + DB_PATH, e);
        }
    }
}
