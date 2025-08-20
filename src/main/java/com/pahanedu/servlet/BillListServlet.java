package com.pahanedu.servlet;

import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.dao.BillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/view_bills")
public class BillListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BillDAO billDAO = new BillDAO();
            List<Bill> bills = billDAO.getAllBills(); // implement this method in your BillDAO

            request.setAttribute("bills", bills);

            String showBillId = request.getParameter("showBillId");
            if (showBillId != null && !showBillId.isEmpty()) {
                request.setAttribute("showBillId", showBillId);
            }

            request.getRequestDispatcher("cashier/view_bills.jsp").forward(request, response);
        } catch (Exception e) {  
         e.printStackTrace();
         request.setAttribute("error", "true");
         request.getRequestDispatcher("cashier/view_bills.jsp").forward(request, response);
     }

    }
}

