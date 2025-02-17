package org.example.demo2.servlet;

import org.example.demo2.model.PostList;
import org.example.demo2.repository.CommentRepository;
import org.example.demo2.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/posts/*")
public class PostServlet extends FixedHttpServlet {
  PostRepository postRepository;
  CommentRepository commentRepository;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    postRepository = new PostRepository();
    commentRepository = new CommentRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pathInfo = req.getPathInfo(); // Gets "/1"
    if (pathInfo != null && pathInfo.length() > 1) {
      String postID = pathInfo.substring(1); // Extracts "1"
      PostList posts = postRepository.getPostByID(postID);

      System.out.println(posts.getContent());

      req.setAttribute("post", posts);
      req.setAttribute("comments", commentRepository.getCommentsByPostID(Long.parseLong(postID)));
      req.getRequestDispatcher("/postDetail.jsp").forward(req, resp);
    } else {
      String page = req.getParameter("page");
      Long currentPage = page == null ? 1 : Long.parseLong(page);
      Long endOfPage = (Long) req.getAttribute("endOfPage");
      long limit = 10;

      if (page == null) {
        page = "1";
      }

      long offset = (Long.parseLong(page) - 1) * limit;

      var pageNumber = postRepository.getEndOfPage();

      log.info("limit {} offset {} pageNumber {}", limit, offset, pageNumber);

      req.setAttribute("endOfPage", pageNumber / limit + 1);
      req.setAttribute("posts", postRepository.getAll(limit, offset));
      req.getRequestDispatcher("/posts.jsp").forward(req, resp);
    }
  }
}
