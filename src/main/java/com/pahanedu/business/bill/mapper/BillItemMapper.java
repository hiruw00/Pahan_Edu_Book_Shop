package com.pahanedu.business.bill.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pahanedu.business.bill.model.BillItem;

public class BillItemMapper {
    public static BillItem map(ResultSet rs) throws SQLException {
        BillItem item = new BillItem();
        item.setId(rs.getInt("id"));
        item.setBillId(rs.getInt("bill_id"));
        item.setItemId(rs.getInt("item_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        return item;
    }
}

