package com.fers.db;

import com.fers.model.User;
import com.fers.util.DatabaseUtil;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO responsible for storing and retrieving users from the database.
 */
public class UserDAO {

    /**
     * Inserts a new user into the database.
     */
    public static boolean insertUser(User user) {

        String sql = """
            INSERT INTO users (user_id, name, status)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getStatus().name());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Failed to insert user:");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves a user from the database using the ID.
     */
    public static User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name")
                );

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
 * Retrieves all users from the database.
 */
public static List<User> getAllUsers() {

    List<User> users = new ArrayList<>();

    String sql = "SELECT * FROM users";

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("name")
            );
            users.add(user);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return users;
    }
}