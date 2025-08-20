package com.pahanedu.persistence.resource.factory.impl;

import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.persistence.resource.factory.ProductFactory;

import java.util.Date;

public class BillFactoryImpl implements ProductFactory<Bill> {

    @Override
    public Bill create(Object... params) {
        // Expected params: id (Integer), customerAccountNumber (Integer), userId (Integer), amount (Double), date (Date)
        int id = params.length > 0 && params[0] instanceof Integer ? (Integer) params[0] : 0;
        int customerAccountNumber = params.length > 1 && params[1] instanceof Integer ? (Integer) params[1] : 0;
        int userId = params.length > 2 && params[2] instanceof Integer ? (Integer) params[2] : 0;
        double amount = params.length > 3 && params[3] instanceof Double ? (Double) params[3] : 0.0;
        Date date = params.length > 4 && params[4] instanceof Date ? (Date) params[4] : new Date();
        String email = params.length > 5 && params[5] instanceof String ? (String) params[5] : "";
        String paymentMethod = params.length > 6 && params[6] instanceof String ? (String) params[6] : "";

        return new Bill(id, customerAccountNumber, userId, amount, date, email, paymentMethod);
    }
}




