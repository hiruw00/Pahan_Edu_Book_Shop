package com.pahanedu.persistence.resource.factory.impl;

import com.pahanedu.business.user.model.Cashier;
import com.pahanedu.business.user.model.User;
import com.pahanedu.persistence.resource.factory.ProductFactory;

public class UserFactoryImpl implements ProductFactory<User> {

    @Override
    public User create(Object... params) {
        // Expecting: int id, String username, String password
        int id = (int) params[0];
        String username = (String) params[1];
        String password = (String) params[2];
        return new Cashier(id, username, password);
    }
}
