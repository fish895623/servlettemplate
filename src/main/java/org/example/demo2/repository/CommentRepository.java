package org.example.demo2.repository;

import org.example.demo2.DatabaseManager;
import org.example.demo2.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentRepository {
  public void saveComment(Comment comment) {
    // Save the comment to the database
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DatabaseManager.getInstance().getConnection();
      preparedStatement = connection.prepareStatement("""
          INSERT INTO comments (post_id, content, author_id)
          VALUE (?, ?, ?)
          """);
      preparedStatement.setLong(1, comment.getPostId());
      preparedStatement.setString(2, comment.getContent());
      preparedStatement.setLong(3, comment.getAuthor_id());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
