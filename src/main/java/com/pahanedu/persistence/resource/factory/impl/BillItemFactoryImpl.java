package com.pahanedu.persistence.resource.factory.impl;

import com.pahanedu.business.bill.model.BillItem;
import com.pahanedu.persistence.resource.factory.ProductFactory;

public class BillItemFactoryImpl implements ProductFactory<BillItem> {

    @Override
    public BillItem create(Object... params) {
        // Expected params:
        // id (Integer, optional),
        // billId (Integer),
        // itemId (Integer),
        // quantity (Integer),
        // price (Double)

        int id = params.length > 0 && params[0] instanceof Integer ? (Integer) params[0] : 0;
        int billId = params.length > 1 && params[1] instanceof Integer ? (Integer) params[1] : 0;
        int itemId = params.length > 2 && params[2] instanceof Integer ? (Integer) params[2] : 0;
        int quantity = params.length > 3 && params[3] instanceof Integer ? (Integer) params[3] : 0;
        double price = params.length > 4 && params[4] instanceof Double ? (Double) params[4] : 0.0;

        BillItem billItem = new BillItem();
        billItem.setId(id);
        billItem.setBillId(billId);
        billItem.setItemId(itemId);
        billItem.setQuantity(quantity);
        billItem.setPrice(price);

        return billItem;
    }
}

