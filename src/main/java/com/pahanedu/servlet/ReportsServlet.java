package com.pahanedu.servlet;

import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT id, customer_account_number, amount, date FROM bills ORDER BY date DESC";

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomerAccountNumber(rs.getInt("customer_account_number"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDate(rs.getTimestamp("date"));
                bills.add(bill);
            }

        } catch (SQLException e) {
            throw new ServletException("Error fetching bills", e);
        }

        request.setAttribute("salesReports", bills);
        request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
    }
}
