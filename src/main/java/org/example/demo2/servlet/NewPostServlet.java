package org.example.demo2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.demo2.model.Post;
import org.example.demo2.model.User;
import org.example.demo2.repository.PostRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/newpost")
public class NewPostServlet extends HttpServlet {
  private PostRepository postRepository;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    postRepository = new PostRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("newpost.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Post post = objectMapper.readValue(req.getReader(), Post.class);
    User user = (User) req.getSession().getAttribute("user");

    log.info("Received post Title: {}", post.getTitle());
    log.info("Received post Content: {}", post.getContent());

    resp.setContentType("application/json");
    try {
      postRepository.savePost(user, post.getTitle(), post.getContent());
    } catch (Exception e) {
      log.error("Error saving post", e);
      resp.setStatus(500);
      resp.getWriter().write("{\"status\": \"error\"}");
      return;
    }

    resp.setStatus(200);
    resp.getWriter().write("{\"status\": \"ok\"}");
  }
}
