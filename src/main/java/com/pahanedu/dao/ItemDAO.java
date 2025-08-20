package com.pahanedu.dao;

import com.pahanedu.business.item.mapper.ItemMapper;
import com.pahanedu.business.item.model.Item;
import com.pahanedu.business.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private final ItemMapper itemMapper = new ItemMapper();

    public void addItem(Item item) {
        String sql = "INSERT INTO items (name, price, quantity, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getAvailability());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DBUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(itemMapper.map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateItem(Item item) {
        String sql = "UPDATE items SET name = ?, price = ?, quantity = ?, availability = ? WHERE id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getAvailability());
            stmt.setInt(5, item.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItems(List<Integer> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) return;

        StringBuilder sql = new StringBuilder("DELETE FROM items WHERE id IN (");
        for (int i = 0; i < itemIds.size(); i++) {
            sql.append("?");
            if (i < itemIds.size() - 1) sql.append(", ");
        }
        sql.append(")");

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < itemIds.size(); i++) {
                stmt.setInt(i + 1, itemIds.get(i));
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
