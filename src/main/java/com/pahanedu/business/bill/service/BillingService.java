package com.pahanedu.business.bill.service;

import com.pahanedu.business.bill.dto.BillDTO;
import com.pahanedu.business.bill.dto.BillItemDTO;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.bill.model.BillItem;
import com.pahanedu.business.util.DBUtil;
import com.pahanedu.dao.BillDAO;
import com.pahanedu.dao.BillItemDAO;
import com.pahanedu.persistence.resource.factory.ProductFactory;
import com.pahanedu.persistence.resource.factory.impl.BillFactoryImpl;
import com.pahanedu.persistence.resource.factory.impl.BillItemFactoryImpl;
import com.pahanedu.persistence.common.observer.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BillingService {

    private final BillDAO billDAO = new BillDAO();
    private final BillItemDAO billItemDAO = new BillItemDAO();

    private final ProductFactory<Bill> billFactory = new BillFactoryImpl();
    private final ProductFactory<BillItem> billItemFactory = new BillItemFactoryImpl();

    // Subject (but no default observers)
    private final BillCreationSubject billCreationSubject = new BillCreationSubject();

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
            try {
                conn = DBUtil.getConnection();
            } catch (Exception e) {
                throw new SQLException("Failed to get DB connection", e);
            }

            conn.setAutoCommit(false);

            billId = billDAO.addBill(bill, conn);

            for (BillItem item : items) {
                item.setBillId(billId);
            }

            try {
                billItemDAO.addBillItems(items, conn);
            } catch (Exception e) {
                throw new SQLException("Failed to add bill items", e);
            }

            conn.commit();

            // Notify observers (only if someone registers in future)
            billCreationSubject.notifyObservers(
                "BILL_CREATED",
                "Bill ID: " + billId + ", Customer: " + bill.getCustomerAccountNumber()
            );

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
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
