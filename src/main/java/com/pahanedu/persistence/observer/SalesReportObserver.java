package com.pahanedu.persistence.observer;

import com.pahanedu.business.bill.model.Bill;

public class SalesReportObserver implements ReportObserver {

    @Override
    public void update(Bill bill) {
        // Log new bill creation (optional)
        System.out.println("New Bill Created: ID=" + bill.getId() +
                ", Customer=" + bill.getCustomerAccountNumber() +
                ", Amount=" + bill.getAmount());
    }
}
