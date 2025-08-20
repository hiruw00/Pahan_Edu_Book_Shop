package com.pahanedu.persistence.resource.factory.impl;

import com.pahanedu.business.customer.model.Customer;
import com.pahanedu.persistence.resource.factory.ProductFactory;

public class CustomerFactoryImpl implements ProductFactory<Customer> {

    @Override
    public Customer create(Object... params) {
        if (params.length < 5) {
            throw new IllegalArgumentException("Expected 5 parameters for Customer");
        }

        int accountNumber = (int) params[0];
        String name = (String) params[1];
        String address = (String) params[2];
        String telephone = (String) params[3];
        int unitsConsumed = (int) params[4];

        return new Customer(accountNumber, name, address, telephone, unitsConsumed);
    }
}
