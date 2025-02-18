package org.example.demo2.servlet;

import org.example.demo2.model.User;
import org.example.demo2.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(value = "/updateUser")
public class UpdateUserServlet extends FixedHttpServlet {
  UserRepository uRepo;

  @Override
  public void init() throws ServletException {
    uRepo = new UserRepository();
    super.init();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath());
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String email = req.getParameter("email");
    String name = req.getParameter("name");
    String role = req.getParameter("role");

    try {
      User tmpUser = new User();
      tmpUser.setEmail(email);
      tmpUser.setName(name);
      tmpUser.setRole(role);

      int result = uRepo.updateUser(tmpUser);

      if (result == 1) {
        System.out.println("User updated successfully");
      } else {
        System.out.println("User update failed");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    resp.sendRedirect(req.getContextPath() + "/mypage/" + req.getParameter("email"));
  }


}
