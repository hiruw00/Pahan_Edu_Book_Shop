package com.pahanedu.service;

import com.pahanedu.dao.ItemDAO;
import com.pahanedu.model.Item;

import java.util.List;

public class ItemService {

    private final ItemDAO itemDAO = new ItemDAO();

    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    public void removeItems(List<Integer> itemIds) {
        itemDAO.removeItems(itemIds);
    }
}

