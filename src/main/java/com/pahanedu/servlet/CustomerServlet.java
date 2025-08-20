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
        "/customers",
        "/addCustomer",
        "/editcustomer",
        "/deletecustomer"
})
public class CustomerServlet extends HttpServlet {

    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/customers":
                handleListCustomers(request, response);
                break;
            case "/deletecustomer":
                handleDeleteCustomer(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/addCustomer":
                handleAddCustomer(request, response);
                break;
            case "/editcustomer":
                handleEditCustomer(request, response);
                break;
        }
    }

    private void handleListCustomers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String success = request.getParameter("success");
        if (success != null) {
            request.setAttribute("success", success);
        }

        // Get CustomerDTO list from service
        List<CustomerDTO> dtoList = service.getAllCustomers();

        // Convert DTO list to Model list for JSP compatibility
        List<Customer> customers = dtoList.stream()
                .map(dto -> new Customer(
                        dto.getAccountNumber(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getTelephone(),
                        dto.getUnitsConsumed()))
                .collect(Collectors.toList());

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/cashier/customers.jsp").forward(request, response);
    }

    private void handleAddCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            CustomerDTO dto = new CustomerDTO(
                    Integer.parseInt(request.getParameter("accountNumber")),
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("telephone"),
                    0 // Set unitsConsumed to 0 initially
            );

            service.addCustomer(dto);
            response.sendRedirect(request.getContextPath() + "/customers?success=1");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private void handleEditCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int originalAccountNumber = Integer.parseInt(request.getParameter("originalAccountNumber"));
            // Fetch existing customer to preserve unitsConsumed
            CustomerDTO existing = service.getCustomerByAccountNumber(originalAccountNumber);

            int unitsConsumed = (existing != null) ? existing.getUnitsConsumed() : 0; // Retain existing value
            CustomerDTO dto = new CustomerDTO(
                    Integer.parseInt(request.getParameter("accountNumber")),
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("telephone"),
                    unitsConsumed // Use the correct value
            );

            boolean updated = service.updateCustomer(originalAccountNumber, dto);

            if (updated) {
                response.sendRedirect("customers?update=success");
            } else {
                response.sendRedirect("customers?update=failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customers?update=error");
        }
    }

    private void handleDeleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String accountStr = request.getParameter("accountNumber");

        if (accountStr == null || accountStr.isEmpty()) {
            response.sendRedirect("customers?delete=error");
            return;
        }

        int accountNumber = Integer.parseInt(accountStr);
        boolean deleted = service.deleteCustomer(accountNumber);

        if (deleted) {
            response.sendRedirect("customers?delete=success");
        } else {
            response.sendRedirect("customers?delete=failure");
        }
    }
}
