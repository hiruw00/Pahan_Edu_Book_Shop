package com.pahanedu.dao;
import com.pahanedu.model.Bill;
import com.pahanedu.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
   public int addBill(Bill bill) {
    String sql = "INSERT INTO bills (customer_account_number, user_id, amount, date) VALUES (?, ?, ?, ?)";
    int billId = -1;

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, bill.getCustomerAccountNumber());
        stmt.setInt(2, bill.getUserId());
        stmt.setDouble(3, bill.getAmount());
        stmt.setDate(4, new java.sql.Date(bill.getDate().getTime()));
        stmt.executeUpdate();

        // Get the generated bill ID
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                billId = rs.getInt(1);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return billId;
}


    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bill bill = new Bill(
                    rs.getInt("id"),
                    rs.getInt("customer_account_number"),
                    rs.getInt("user_id"), // Added user_id
                    rs.getDouble("amount"),
                    rs.getDate("date")
                );
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
}