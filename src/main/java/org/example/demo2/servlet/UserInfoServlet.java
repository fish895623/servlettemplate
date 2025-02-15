package org.example.demo2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userinfo/*")
public class UserInfoServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pathInfo = request.getPathInfo(); // Gets "/1"
    if (pathInfo != null && pathInfo.length() > 1) {
      String userId = pathInfo.substring(1); // Extracts "1"
      request.setAttribute("userId", userId);

      request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
    }

  }
}
