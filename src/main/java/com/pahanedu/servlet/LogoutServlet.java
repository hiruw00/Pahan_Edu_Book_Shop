package com.pahanedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don’t create a new one
        if (session != null) {
            session.invalidate(); // End the session
        }
        response.sendRedirect("login.jsp"); // Redirect to login
    }
}

