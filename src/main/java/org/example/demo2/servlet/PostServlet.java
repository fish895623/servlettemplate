package org.example.demo2.servlet;

import org.example.demo2.repository.PostRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/posts")
public class PostServlet extends HttpServlet {
  PostRepository postRepository;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    postRepository = new PostRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("posts", postRepository.getAll());
    req.getRequestDispatcher("posts.jsp").forward(req, resp);
  }
}
