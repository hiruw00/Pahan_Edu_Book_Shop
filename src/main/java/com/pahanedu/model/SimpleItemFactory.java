package com.pahanedu.model;

public class SimpleItemFactory implements ItemFactory {

    @Override
    public Item createItem(String name, double price, int quantity, String availability) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setAvailability(availability);
        return item;
    }
}

