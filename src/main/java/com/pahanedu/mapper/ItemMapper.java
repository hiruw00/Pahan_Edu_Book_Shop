package com.pahanedu.mapper;

import com.pahanedu.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {

    public Item map(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getDouble("price"));
        item.setQuantity(rs.getInt("quantity"));
        item.setAvailability(rs.getString("availability"));
        return item;
    }
}

