package com.pahanedu.servlet;

import com.pahanedu.business.bill.dto.BillDTO;
import com.pahanedu.business.bill.dto.BillItemDTO;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.bill.model.BillItem;
import com.pahanedu.business.bill.service.BillingService;
import com.pahanedu.dao.BillDAO;
import com.pahanedu.dao.BillItemDAO;
import com.pahanedu.persistence.resource.factory.impl.BillFactoryImpl;
import com.pahanedu.persistence.resource.factory.impl.BillItemFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {
        "/create_bill",
        "/GetBillDetailsServlet"
})
public class BillServlet extends HttpServlet {

    private final BillingService billingService = new BillingService();
    private final BillFactoryImpl billFactory = new BillFactoryImpl();
    private final BillItemFactoryImpl billItemFactory = new BillItemFactoryImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/create_bill".equals(request.getServletPath())) {
            handleCreateBill(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/GetBillDetailsServlet".equals(request.getServletPath())) {
            handleGetBillDetails(request, response);
        }
    }

    private void handleCreateBill(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String accountStr = request.getParameter("customer_account_number");
            String amountStr = request.getParameter("amount");
            String source = request.getParameter("source");
            String email = request.getParameter("email"); // ✅ new
            String paymentMethod = request.getParameter("payment_method"); // ✅ new

            if (accountStr == null || amountStr == null || accountStr.isEmpty() || amountStr.isEmpty()) {
                redirectWithError(response, source);
                return;
            }

            int customerAccountNumber = Integer.parseInt(accountStr);
            double totalAmount = Double.parseDouble(amountStr);
            int createdBy = 2; // Hardcoded for now

            // Create BillDTO directly using its constructor
            BillDTO billDTO = new BillDTO(
                0, // ID will be auto-generated
                customerAccountNumber,
                createdBy,
                totalAmount,
                new Date(),
                email,
                paymentMethod
            );
            


            String[] itemIds = request.getParameterValues("item_ids[]");
            String[] quantities = request.getParameterValues("quantities[]");
            String[] prices = request.getParameterValues("prices[]");

            List<BillItemDTO> billItemDTOs = new ArrayList<>();
            if (itemIds != null && quantities != null && prices != null) {
                for (int i = 0; i < itemIds.length; i++) {
                    if (itemIds[i] == null || quantities[i] == null || prices[i] == null ||
                        itemIds[i].isEmpty() || quantities[i].isEmpty() || prices[i].isEmpty()) {
                        continue;
                    }
                    BillItemDTO itemDTO = new BillItemDTO(
                        0, // ID will be auto-generated
                        0, // Bill ID will be set later
                        Integer.parseInt(itemIds[i]),
                        Integer.parseInt(quantities[i]),
                        Double.parseDouble(prices[i])
                    );
                    billItemDTOs.add(itemDTO);
                }
            }

            int billId = billingService.createBillWithItems(billDTO, billItemDTOs);

            redirectWithSuccess(response, source, billId);

        } catch (Exception e) {
            e.printStackTrace();
            redirectWithError(response, request.getParameter("source"));
        }
    }

    private void handleGetBillDetails(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int billId = Integer.parseInt(request.getParameter("billId"));

            BillDAO billDAO = new BillDAO();
            Bill bill = billDAO.getBillById(billId);

            if (bill == null) {
                response.getWriter().println("<p style='color:red;'>Bill not found.</p>");
                return;
            }

            BillDTO billDTO = new BillDTO(
                bill.getId(),
                bill.getCustomerAccountNumber(),
                bill.getUserId(),
                bill.getAmount(),
                bill.getDate(),
                bill.getEmail(),
                bill.getPaymentMethod()
            );

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<h3>Bill ID: " + billDTO.getId() + "</h3>");
            out.println("<p>Customer Account #: " + billDTO.getCustomerAccountNumber() + "</p>");
            out.println("<p>Date: " + billDTO.getDate() + "</p>");
            out.println("<p>Amount: Rs. " + billDTO.getAmount() + "</p>");
            out.println("<hr>");
            out.println("<h4>Items:</h4>");
            out.println("<table border='1' style='width: 100%; border-collapse: collapse;'>");
            out.println("<tr><th>Item ID</th><th>Quantity</th><th>Price</th></tr>");

            BillItemDAO billItemDAO = new BillItemDAO();
            List<BillItem> items = billItemDAO.getBillItemsByBillId(billId);

            // Convert BillItem to BillItemDTO
            List<BillItemDTO> itemDTOs = items.stream()
                .map(item -> new BillItemDTO(
                    item.getId(),
                    item.getBillId(),
                    item.getItemId(),
                    item.getQuantity(),
                    item.getPrice()
                ))
                .collect(Collectors.toList());

            for (BillItemDTO item : itemDTOs) {
                out.println("<tr>");
                out.println("<td>" + item.getItemId() + "</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>Rs. " + item.getPrice() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p style='color:red;'>Failed to load bill details.</p>");
        }
    }

    private void redirectWithSuccess(HttpServletResponse response, String source, int billId) throws IOException {
        if ("admin".equalsIgnoreCase(source)) {
            response.sendRedirect("admin/view_all_bills.jsp?success=true&showBillId=" + billId);
        } else {
            response.sendRedirect("cashier/view_bills.jsp?success=true&showBillId=" + billId);
        }
    }

    private void redirectWithError(HttpServletResponse response, String source) throws IOException {
        if ("admin".equalsIgnoreCase(source)) {
            response.sendRedirect("admin/create_bill.jsp?error=true");
        } else {
            response.sendRedirect("cashier/create_bill.jsp?error=true");
        }
    }
}

