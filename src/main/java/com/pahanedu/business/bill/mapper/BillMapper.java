package com.pahanedu.business.bill.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pahanedu.business.bill.model.Bill;

public class BillMapper {
    public static Bill map(ResultSet rs) throws SQLException {
        return new Bill(
            rs.getInt("id"),
            rs.getInt("customer_account_number"),
            rs.getInt("user_id"),
            rs.getDouble("amount"),
            rs.getDate("date"),
            rs.getString("email"),          // new field
            rs.getString("payment_method")  // new field
        );
    }
}
