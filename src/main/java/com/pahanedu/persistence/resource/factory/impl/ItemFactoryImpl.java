package com.pahanedu.persistence.resource.factory.impl;

import com.pahanedu.business.item.model.Item;
import com.pahanedu.persistence.resource.factory.ProductFactory;

public class ItemFactoryImpl implements ProductFactory<Item> {

    @Override
    public Item create(Object... params) {
        // Expecting: int id, String name, double price, int quantity, String availability
        int id = (int) params[0];
        String name = (String) params[1];
        double price = (double) params[2];
        int quantity = (int) params[3];
        String availability = (String) params[4];
        return new Item(id, name, price, quantity, availability);
    }
}

