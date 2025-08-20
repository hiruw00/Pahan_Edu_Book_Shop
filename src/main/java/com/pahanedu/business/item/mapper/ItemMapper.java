package com.pahanedu.business.item.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pahanedu.business.item.model.Item;

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

