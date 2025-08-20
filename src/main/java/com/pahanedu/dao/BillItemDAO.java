package com.pahanedu.dao;

import com.pahanedu.business.bill.mapper.BillItemMapper;
import com.pahanedu.business.bill.model.BillItem;
import com.pahanedu.business.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillItemDAO {

    public void addBillItem(BillItem item) {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getBillId());
            stmt.setInt(2, item.getItemId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBillItems(List<BillItem> items) {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (BillItem item : items) {
                stmt.setInt(1, item.getBillId());
                stmt.setInt(2, item.getItemId());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBillItems(List<BillItem> items, Connection conn) throws Exception {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (BillItem item : items) {
                stmt.setInt(1, item.getBillId());
                stmt.setInt(2, item.getItemId());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<BillItem> getBillItemsByBillId(int billId) throws Exception {
        List<BillItem> items = new ArrayList<>();
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bill_items WHERE bill_id = ?")) {

            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(BillItemMapper.map(rs));
                }
            }
        }
        return items;
    }
}
