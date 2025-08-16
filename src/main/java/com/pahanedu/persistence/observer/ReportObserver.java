package com.pahanedu.persistence.observer;

import com.pahanedu.business.bill.model.Bill;

public interface ReportObserver {
    void update(Bill bill);
}
