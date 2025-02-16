package org.example.demo2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo2.model.Comment;
import org.example.demo2.repository.CommentRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/comments")
public class CommentServlet extends HttpServlet {
  CommentRepository commentRepository;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    commentRepository = new CommentRepository();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Comment comment = objectMapper.readValue(req.getReader(), Comment.class);

    commentRepository.saveComment(comment);
  }
}
