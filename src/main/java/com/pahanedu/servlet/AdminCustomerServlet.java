package com.pahanedu.servlet;

import com.pahanedu.business.customer.dto.CustomerDTO;
import com.pahanedu.business.customer.model.Customer;
import com.pahanedu.business.customer.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {
    "/admin/customers",
    "/admin/customers/add",
    "/admin/customers/edit",
    "/admin/customers/delete"
})
public class AdminCustomerServlet extends HttpServlet {

    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CustomerDTO> dtoList = service.getAllCustomers();

        List<Customer> customers = dtoList.stream()
                .map(dto -> new Customer(
                        dto.getAccountNumber(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getTelephone(),
                        dto.getUnitsConsumed()))
                .collect(Collectors.toList());

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/admin/customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/admin/customers/add":
                handleAddCustomer(request, response);
                break;
            case "/admin/customers/edit":
                handleEditCustomer(request, response);
                break;
            case "/admin/customers/delete":
                handleDeleteCustomer(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/customers");
        }
    }

    private void handleAddCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CustomerDTO dto = new CustomerDTO(
                    Integer.parseInt(request.getParameter("accountNumber")),
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("telephone"),
                    0 // Set unitsConsumed to 0 initially
            );
            service.addCustomer(dto);
            response.sendRedirect(request.getContextPath() + "/admin/customers?success=add");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error adding customer: " + e.getMessage());
        }
    }

    private void handleEditCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int originalAccountNumber = Integer.parseInt(request.getParameter("originalAccountNumber"));
            int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed")); // Retain existing value
            CustomerDTO dto = new CustomerDTO(
                    Integer.parseInt(request.getParameter("accountNumber")),
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("telephone"),
                    unitsConsumed // Use the correct value
            );
            boolean updated = service.updateCustomer(originalAccountNumber, dto);
            if (updated) {
                response.sendRedirect(request.getContextPath() + "/admin/customers?success=edit");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/customers?failure=edit");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/customers?error=edit");
        }
    }

    private void handleDeleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // For bulk delete: receive multiple account numbers from checkboxes
            String[] accountNumbers = request.getParameterValues("accountNumbers");
            boolean allDeleted = true;
            if (accountNumbers != null && accountNumbers.length > 0) {
                for (String accStr : accountNumbers) {
                    int accNum = Integer.parseInt(accStr);
                    boolean deleted = service.deleteCustomer(accNum);
                    if (!deleted) allDeleted = false;
                }
            } else {
                // If no checkboxes selected
                response.sendRedirect(request.getContextPath() + "/admin/customers?failure=delete_none_selected");
                return;
            }

            if (allDeleted) {
                response.sendRedirect(request.getContextPath() + "/admin/customers?success=delete");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/customers?failure=delete");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/customers?error=delete");
        }
    }
}
