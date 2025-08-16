package com.pahanedu.service;

import java.util.List;

import com.pahanedu.dao.UserDAO;
import com.pahanedu.model.Cashier;


public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean addCashier(String username, String password) {
        Cashier cashier = new Cashier(0, username, password);
        return userDAO.insertCashier(cashier);
    }
   public List<Cashier> getAllCashiers() {
    return userDAO.getAllCashiers();
}

public boolean deleteCashierById(int id) {
    return userDAO.deleteCashier(id);
}

}
