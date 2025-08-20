package com.pahanedu.business.item.service;

import com.pahanedu.business.item.dto.ItemDTO;
import com.pahanedu.business.item.model.Item;
import com.pahanedu.dao.ItemDAO;
import com.pahanedu.persistence.resource.factory.impl.ItemFactoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ItemService {
    private final ItemDAO itemDAO = new ItemDAO();
    private final ItemFactoryImpl itemFactory = new ItemFactoryImpl();

    public void addItem(ItemDTO dto) {
        Item item = itemFactory.create(dto.getId(), dto.getName(), dto.getPrice(), dto.getQuantity(), dto.getAvailability());
        itemDAO.addItem(item);
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemDAO.getAllItems();
        return items.stream()
                .map(i -> new ItemDTO(i.getId(), i.getName(), i.getPrice(), i.getQuantity(), i.getAvailability()))
                .collect(Collectors.toList());
    }

    public void updateItem(ItemDTO dto) {
        Item item = itemFactory.create(dto.getId(), dto.getName(), dto.getPrice(), dto.getQuantity(), dto.getAvailability());
        itemDAO.updateItem(item);
    }

    public void removeItems(List<Integer> ids) {
        itemDAO.removeItems(ids);
    }
}
