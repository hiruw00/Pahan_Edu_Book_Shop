package com.pahanedu.business.bill.service;

import com.pahanedu.business.bill.dto.BillDTO;
import com.pahanedu.business.bill.dto.BillItemDTO;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.bill.model.BillItem;
import com.pahanedu.business.customer.service.CustomerService;
import com.pahanedu.business.util.DBUtil;
import com.pahanedu.dao.BillDAO;
import com.pahanedu.dao.BillItemDAO;
import com.pahanedu.persistence.observer.ReportObserver;
import com.pahanedu.persistence.resource.factory.impl.BillFactoryImpl;
import com.pahanedu.persistence.resource.factory.impl.BillItemFactoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BillingService {

    private static final Logger LOGGER = Logger.getLogger(BillingService.class.getName());
    private final BillDAO billDAO = new BillDAO();
    private final BillItemDAO billItemDAO = new BillItemDAO();
    private final CustomerService customerService = new CustomerService();
    private final BillFactoryImpl billFactory = new BillFactoryImpl();
    private final BillItemFactoryImpl billItemFactory = new BillItemFactoryImpl();

    // Observer list
    private final List<ReportObserver> observers = new ArrayList<>();

    // Add observer
    public void addObserver(ReportObserver observer) {
        observers.add(observer);
    }

    // Notify all observers
    private void notifyObservers(Bill bill) {
        for (ReportObserver observer : observers) {
            observer.update(bill);
        }
    }

    public int createBillWithItems(BillDTO billDTO, List<BillItemDTO> itemDTOs) throws SQLException {
        Bill bill = billFactory.create(
            billDTO.getId(),
            billDTO.getCustomerAccountNumber(),
            billDTO.getUserId(),
            billDTO.getAmount(),
            billDTO.getDate()
        );

        List<BillItem> items = itemDTOs.stream()
            .map(dto -> billItemFactory.create(
                dto.getId(),
                dto.getBillId(),
                dto.getItemId(),
                dto.getQuantity(),
                dto.getPrice()
            ))
            .collect(Collectors.toList());

        int billId = -1;
        Connection conn = null;
        try {
            conn = DBUtil.getInstance().getConnection();
            conn.setAutoCommit(false);

            // Add bill
            billId = billDAO.addBill(bill, conn);

            // Set billId for items
            for (BillItem item : items) {
                item.setBillId(billId);
            }

            // Add bill items
            try {
                billItemDAO.addBillItems(items, conn);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error adding bill items", e);
                throw new SQLException("Failed to add bill items", e);
            }

            // Calculate total units consumed
            int totalUnits = items.stream().mapToInt(BillItem::getQuantity).sum();

            // Update units consumed for the customer
            customerService.updateUnitsConsumedForBill(bill.getCustomerAccountNumber(), totalUnits);

            conn.commit();

            // Notify observers about the new bill
            notifyObservers(bill);

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            LOGGER.log(Level.SEVERE, "Error creating bill", e);
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }

        return billId;
    }
}
