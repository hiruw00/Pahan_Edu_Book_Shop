package com.pahanedu.dao;

import com.pahanedu.model.BillItem;
import com.pahanedu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillItemDAO {

    // Insert a single BillItem
    public void addBillItem(BillItem item) {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
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

    // Optional: Insert multiple BillItems at once
    public void addBillItems(List<BillItem> items) {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
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
    public List<BillItem> getBillItemsByBillId(int billId) throws Exception {
    List<BillItem> items = new ArrayList<>();
    Connection conn = DBUtil.getConnection();
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bill_items WHERE bill_id = ?");
    stmt.setInt(1, billId);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        BillItem item = new BillItem();
        item.setId(rs.getInt("id"));
        item.setBillId(rs.getInt("bill_id"));
        item.setItemId(rs.getInt("item_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        items.add(item);
    }
    return items;
}

}

