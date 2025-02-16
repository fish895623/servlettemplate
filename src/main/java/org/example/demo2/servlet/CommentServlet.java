package org.example.demo2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo2.model.Comment;
import org.example.demo2.model.User;
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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json");

    ObjectMapper objectMapper = new ObjectMapper();
    Comment comment = objectMapper.readValue(req.getReader(), Comment.class);

    User user = (User) req.getSession().getAttribute("user");

    comment.setAuthorId(user.getId());

    System.out.println(objectMapper.toString());


    try {
      commentRepository.saveComment(comment);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"status\": \"error\"}");
      return;
    }

    resp.setStatus(200);
    resp.getWriter().write("{\"status\": \"ok\"}");
  }
}
