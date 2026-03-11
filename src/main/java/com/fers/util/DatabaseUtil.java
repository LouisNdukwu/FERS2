package com.fers.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil{
    private static final String DB_FOLDER = "data";
    private static final String DB_FILE = "fers.db";
    private static final String DB_URL;
    
    static{
        try{
            Path folder = Paths.get(DB_FOLDER);
            if (!Files.exists(folder)){
                Files.createDirectories(folder);
            }
            Path dbPath = folder.resolve(DB_FILE);
            DB_URL = "jdbc:sqlite:" + dbPath.toAbsolutePath();
        } catch (Exception e){
            throw new RuntimeException("Failed to initialize databse path", e);
        }
    }
    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(DB_URL);
    }
}