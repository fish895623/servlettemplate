package org.example.demo2.repository;

import org.example.demo2.DatabaseManager;
import org.example.demo2.model.Comment;
import org.example.demo2.model.CommentDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public List<CommentDAO> getCommentsByPostID(Long postID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    List<CommentDAO> data = new ArrayList<>();

    try {
      connection = DatabaseManager.getInstance().getConnection();
      preparedStatement = connection.prepareStatement("""
          SELECT c.id, post_id, content, c.author_id, u.name AS author_name, created_at
          FROM comments c
          JOIN user u ON c.author_id = u.id
          WHERE post_id = ?
          ORDER BY created_at DESC
          """);
      preparedStatement.setLong(1, postID);
      resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    try {
      while (resultSet.next()) {
        var d = CommentDAO.builder()
            .id(resultSet.getLong("id"))
            .author_id(resultSet.getLong("author_id"))
            .authorName(resultSet.getString("author_name"))
            .postId(resultSet.getLong("post_id"))
            .content(resultSet.getString("content"))
            .createdAt(resultSet.getTimestamp("created_at"))
            .build();
        data.add(d);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return data;
  }
}
