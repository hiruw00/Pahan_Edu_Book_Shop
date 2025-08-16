package com.pahanedu.dao;

import com.pahanedu.business.bill.mapper.BillMapper;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public int addBill(Bill bill) {
        String sql = "INSERT INTO bills (customer_account_number, user_id, amount, date) VALUES (?, ?, ?, ?)";
        int generatedId = -1;
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, bill.getCustomerAccountNumber());
            stmt.setInt(2, bill.getUserId());
            stmt.setDouble(3, bill.getAmount());
            stmt.setDate(4, new java.sql.Date(bill.getDate().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) generatedId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public int addBill(Bill bill, Connection conn) throws SQLException {
        String sql = "INSERT INTO bills (customer_account_number, user_id, amount, date) VALUES (?, ?, ?, ?)";
        int generatedId = -1;
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bill.getCustomerAccountNumber());
            stmt.setInt(2, bill.getUserId());
            stmt.setDouble(3, bill.getAmount());
            stmt.setDate(4, new java.sql.Date(bill.getDate().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) generatedId = rs.getInt(1);
            }
        }
        return generatedId;
    }

    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DBUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                bills.add(BillMapper.map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }

    public Bill getBillById(int billId) {
        Bill bill = null;
        String sql = "SELECT * FROM bills WHERE id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) bill = BillMapper.map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
    }
}
