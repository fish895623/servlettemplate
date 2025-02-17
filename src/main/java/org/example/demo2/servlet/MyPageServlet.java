package org.example.demo2.servlet;

import org.example.demo2.model.User;
import org.example.demo2.repository.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/mypage/*")
public class MyPageServlet extends FixedHttpServlet {

  UserRepository uRepo;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    uRepo = new UserRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pathInfo = req.getPathInfo();
    // Gets email from the path
    String email = pathInfo.substring(1);
    try {
      User user = uRepo.getUserByEmail(email);
      req.setAttribute("user", user);

    } catch (SQLException e) {
      System.err.println("Error getting user by email " + e);
      resp.setStatus(500);
      resp.getWriter().write("{\"status\": \"error\"}");
      return;
    }

    resp.setStatus(200);
    resp.getWriter().write("{\"status\": \"ok\"}");

    req.getRequestDispatcher("/myPage.jsp").forward(req, resp);
  }

}
