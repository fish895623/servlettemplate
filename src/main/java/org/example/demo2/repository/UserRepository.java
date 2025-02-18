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

  // getUserByEmail ----------------------------------------------------------------
  // This method is used to get user by email
  // PARAMETERS: String email
  // RETURNS: User
  public User getUserByEmail(String email) throws SQLException {
    var connection = DatabaseManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("""
        SELECT id, email, name, role FROM user WHERE BINARY email = ?
        """);
    preparedStatement.setString(1, email);

    ResultSet resultSet = preparedStatement.executeQuery();

    // parse result to User object
    User tmp = new User();
    if (resultSet.next()) {
      tmp.setId(resultSet.getLong("id"));
      tmp.setEmail(resultSet.getString("email"));
      tmp.setName(resultSet.getString("name"));
      tmp.setRole(resultSet.getString("role"));
    }

    return tmp;
  }

  // updateUser ----------------------------------------------------------------
  // This method is used to update user
  // PARAMETERS: User user
  // RETURNS: boolean
  public int updateUser(User user) throws SQLException {
    var connection = DatabaseManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("""
        UPDATE USER SET name = ?, role = ? WHERE BINARY email = ?
        """);
    preparedStatement.setString(1, user.getName());
    preparedStatement.setString(2, user.getRole());
    preparedStatement.setString(3, user.getEmail());

    int result = preparedStatement.executeUpdate();

    if (result == 0) {
      throw new SQLException("User not found");
    } else {
      return result;
    }
  }
}
