package com.digi.repository;

import com.digi.model.User;

import java.sql.*;

public class UserRepository {
    public static void create(Connection connection, String name, String email, int age, String password) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("insert into users (users_name,users_email,users_age,users_password) values (?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, password);
            preparedStatement.execute();
            System.out.println("Added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int users_id = resultSet.getInt("users_id");
                String users_name = resultSet.getString("users_name");
                String users_email = resultSet.getString("users_email");
                int users_age = resultSet.getInt("users_age");
                String users_password = resultSet.getString("users_password");
                User user = new User(users_id, users_name, users_email, users_age, users_password);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Connection connection, String name, String newName, String newEmail, int newAge, String newPassword) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET users_name = ?, users_email = ?, users_age = ?, users_password = ? WHERE users_name = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setInt(3, newAge);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setString(5, name);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Connection connection, String name) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE users_name = ?");
            preparedStatement.setString(1, name);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Deleted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
