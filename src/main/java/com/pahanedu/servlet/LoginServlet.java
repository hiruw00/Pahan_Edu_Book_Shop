package com.pahanedu.servlet;

import com.pahanedu.dao.UserDAO;
import com.pahanedu.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO(); // create UserDAO instance
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check user in DB
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            // Successful login → create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            // Redirect to role-based dashboard
            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("admin_dashboard.jsp");
            } else if ("cashier".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("cashier_dashboard.jsp");
            } else {
                // unknown role
                response.sendRedirect("login.jsp?error=Invalid+user+role");
            }
        } else {
            // Failed login
            response.sendRedirect("login.jsp?error=Invalid+username+or+password");
        }
    }
}
