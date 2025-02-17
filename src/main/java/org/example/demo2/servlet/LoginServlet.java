package org.example.demo2.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.example.demo2.model.User;
import org.example.demo2.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends FixedHttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html;charset=UTF-8");

    var dispatcher = req.getRequestDispatcher("login.jsp");

    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    HttpSession session = req.getSession();

    UserRepository userRepository = new UserRepository();
    User user = null;
    try {
      user = userRepository.checkUser(email, password);
      log(String.format("User: %s", user.getEmail()));
      if (user.getEmail() == null) {
        // FAILED to login
        req.getRequestDispatcher("loginFailed.jsp").forward(req, resp);
        return;
      }
      session.setAttribute("user", user);
    } catch (SQLException e) {
      log("Error: ", e);
    }

    var a = (User) session.getAttribute("user");

    log(String.format("Email: %s, Password: %s", email, password));

    resp.sendRedirect(req.getContextPath() + "/");
  }
}
