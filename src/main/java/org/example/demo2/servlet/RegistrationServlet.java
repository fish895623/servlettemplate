package org.example.demo2.servlet;

import org.example.demo2.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends FixedHttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log("doGet");
    RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password1");
    String password2 = req.getParameter("password2");
    String privileges = req.getParameter("privileges");
    String name = req.getParameter("name");

    log(String.format("email: %s, privileges: %s Name: %s", email, privileges, name));

    if (password.equals(password2)) {
      try {
        new UserRepository().saveUser(email, password, name, privileges);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    HttpSession session = req.getSession();
    session.setAttribute("email", email);
    session.setAttribute("password", password);

    resp.sendRedirect(req.getContextPath() + "/login");
  }
}
