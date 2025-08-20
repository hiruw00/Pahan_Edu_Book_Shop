package com.pahanedu.business.user.service;

import com.pahanedu.business.user.dto.UserDTO;
import com.pahanedu.business.user.model.Cashier;
import com.pahanedu.business.user.model.User;
import com.pahanedu.dao.UserDAO;
import com.pahanedu.persistence.resource.factory.impl.UserFactoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final UserFactoryImpl userFactory = new UserFactoryImpl();

    public boolean addCashier(UserDTO userDTO) {
        // Use factory to create User (Cashier)
        User user = userFactory.create(0, userDTO.getUsername(), userDTO.getPassword());
        return userDAO.insertCashier((Cashier) user);
    }

    public List<UserDTO> getAllCashiers() {
        List<Cashier> cashiers = userDAO.getAllCashiers();
        return cashiers.stream()
                .map(c -> new UserDTO(c.getId(), c.getUsername(), null, c.getRole()))
                .collect(Collectors.toList());
    }

    public boolean deleteCashierById(int id) {
        return userDAO.deleteCashier(id);
    }
}
