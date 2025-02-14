package org.example.demo2.repository;

import org.example.demo2.DatabaseManager;
import org.example.demo2.Prop;
import org.example.demo2.model.Post;
import org.example.demo2.model.PostList;
import org.example.demo2.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostRepository {
  private Path uploadPath;

  public PostRepository() {
    uploadPath = Paths.get(Prop.get("storage.path"));
    try {
      Files.createDirectories(uploadPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<PostList> getAll() {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    List<PostList> data = new ArrayList<>();

    try {
      connection = DatabaseManager.getInstance().getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    try {
      preparedStatement = connection.prepareStatement("""
          SELECT p.id AS postid, u.id AS userid, u.name, title, content, created_at
          FROM posts p
          JOIN user u ON p.author = u.id
          ORDER BY created_at DESC
          """);

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        var val = new PostList();
        val.setId(resultSet.getLong("postid"));
        val.setAuthorID(resultSet.getLong("userid"));
        val.setAuthorName(resultSet.getString("name"));
        val.setTitle(resultSet.getString("title"));
        val.setContent(loadContent(resultSet.getString("content")));
        val.setCreated_at(resultSet.getTimestamp("created_at"));

        data.add(val);
      }


      return data;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public List<Post> loadPosts(Long id) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    List<Post> data = new ArrayList<>();

    try {
      connection = DatabaseManager.getInstance().getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    try {
      preparedStatement = connection.prepareStatement("""
          SELECT (id, author, content, created_at) FROM posts WHERE author = ?
          """);
      preparedStatement.setLong(1, id);

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        new Post();
        data.add(Post.builder().id(resultSet.getLong("id")).author(resultSet.getLong("author"))
            .content(loadContent(resultSet.getString("content"))).created_at(resultSet.getTimestamp("created_at"))
            .build());
      }


      return data;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void savePost(User user, String title, String content) {
    Connection connection;
    PreparedStatement preparedStatement;
    String uuid = UUID.randomUUID().toString();

    try {
      connection = DatabaseManager.getInstance().getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    try {
      preparedStatement = connection.prepareStatement("""
          INSERT INTO posts (author, title, content) VALUES (?, ?, ?)
          """);
      preparedStatement.setLong(1, user.getId());
      preparedStatement.setString(2, title);
      preparedStatement.setString(3, uuid);
      saveContent(uuid, content);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Save Content to file
   *
   * @param uuid    UUID of the post
   * @param content Content of the post
   */
  private void saveContent(String uuid, String content) {
    Path path = uploadPath.resolve(uuid + ".md");
    try {
      Files.writeString(path, content);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Load content from file
   *
   * @param uuid UUID of the post
   * @return Content of the post
   */
  private String loadContent(String uuid) {
    Path path = uploadPath.resolve(uuid + ".md");
    try {
      return Files.readString(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
