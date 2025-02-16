package org.example.demo2.servlet;

import org.example.demo2.model.PostList;
import org.example.demo2.repository.PostRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/userinfo/*")
public class UserInfoServlet extends HttpServlet {
  PostRepository postRepository;
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    postRepository = new PostRepository();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pathInfo = request.getPathInfo(); // Gets "/1"
    if (pathInfo != null && pathInfo.length() > 1) {
      String userId = pathInfo.substring(1); // Extracts "1"
      List<PostList> posts = postRepository.getByUserId(userId);


      request.setAttribute("userId", userId);
      request.setAttribute("posts", posts);

      request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
    }
  }
}
