package com.pahanedu.servlet;

import com.pahanedu.dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletecustomer") // match this with your JSP href
public class DeleteCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountStr = request.getParameter("accountNumber");

        if (accountStr == null || accountStr.isEmpty()) {
            response.sendRedirect("customers?delete=error");
            return;
        }

        int accountNumber = Integer.parseInt(accountStr);
        CustomerDAO dao = new CustomerDAO();
        boolean deleted = dao.deleteCustomer(accountNumber);

        if (deleted) {
            response.sendRedirect("customers?delete=success");
        } else {
            response.sendRedirect("customers?delete=failure");
        }
    }
}

