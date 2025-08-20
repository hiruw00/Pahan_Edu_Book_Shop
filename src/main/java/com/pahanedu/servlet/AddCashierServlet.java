package com.pahanedu.servlet;

import com.pahanedu.business.user.dto.UserDTO;
import com.pahanedu.business.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/addCashier")
public class AddCashierServlet extends HttpServlet {
    private final UserService userService = new UserService();

    // Handle add and delete operations
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if deletion is requested
        String[] deleteIds = request.getParameterValues("deleteIds");

        if (deleteIds != null) {
            for (String idStr : deleteIds) {
                try {
                    int id = Integer.parseInt(idStr);
                    userService.deleteCashierById(id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect(request.getContextPath() + "/addCashier?status=deleted");
            return;
        }

        // Otherwise, handle adding cashier
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = userService.addCashier(new UserDTO(0, username, password, "cashier"));

        if (success) {
            response.sendRedirect(request.getContextPath() + "/addCashier?status=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/addCashier?status=error");
        }
    }

    // Handle listing all cashiers
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDTO> cashiers = userService.getAllCashiers();
        request.setAttribute("cashiers", cashiers);
        request.getRequestDispatcher("/admin/add_cashier.jsp").forward(request, response);
    }
}
