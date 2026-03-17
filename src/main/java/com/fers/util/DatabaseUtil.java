package com.fers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for creating database connections.
 * Supports both production and test databases.
 */
public class DatabaseUtil {

    private static final String PROD_DB_URL = "jdbc:sqlite:fers.db";
    private static final String TEST_DB_URL = "jdbc:sqlite:fers_test.db";

    private static boolean testMode = false;

    /**
     * Enables test mode so that the test database is used.
     */
    public static void enableTestMode() {
        testMode = true;
    }

    /**
     * Returns a connection to the appropriate database.
     */
    public static Connection getConnection() throws SQLException {
        String dbUrl = testMode ? TEST_DB_URL : PROD_DB_URL;
        return DriverManager.getConnection(dbUrl);
    }
}