package org.example.demo2.repository;

import org.example.demo2.DatabaseManager;
import org.example.demo2.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
  public void saveUser(String email, String password, String name, String privileges) throws SQLException {
    var connection = DatabaseManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("""
        INSERT INTO user (email, password, name, role) VALUES (?, ?, ?, ?)
        """);
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, password);
    preparedStatement.setString(3, name);
    preparedStatement.setString(4, privileges);

    preparedStatement.executeUpdate();
  }

  public User checkUser(String email, String password) throws SQLException {
    var connection = DatabaseManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("""
        SELECT id, email FROM user WHERE email = ? AND password = ?
        """);
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();

    // parse result to User object
    var data = new User();
    if (resultSet.next()) {
      data.setId(resultSet.getLong("id"));
      data.setEmail(resultSet.getString("email"));
    }

    return data;
  }
}
