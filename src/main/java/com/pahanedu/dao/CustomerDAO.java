package com.pahanedu.dao;

import com.pahanedu.business.customer.mapper.CustomerMapper;
import com.pahanedu.business.customer.model.Customer;
import com.pahanedu.business.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final CustomerMapper customerMapper = new CustomerMapper();

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (account_number, name, address, telephone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getTelephone());
            stmt.setInt(5, customer.getUnitsConsumed());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DBUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(customerMapper.map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByAccountNumber(int accountNumber) {
        String sql = "SELECT * FROM customers WHERE account_number = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return customerMapper.map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomer(int originalAccountNumber, Customer updatedCustomer) {
        String sql = "UPDATE customers SET account_number=?, name=?, address=?, telephone=?, units_consumed=? WHERE account_number=?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, updatedCustomer.getAccountNumber());
            stmt.setString(2, updatedCustomer.getName());
            stmt.setString(3, updatedCustomer.getAddress());
            stmt.setString(4, updatedCustomer.getTelephone());
            stmt.setInt(5, updatedCustomer.getUnitsConsumed());
            stmt.setInt(6, originalAccountNumber);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int accountNumber) {
        String sql = "DELETE FROM customers WHERE account_number = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteMultipleCustomers(List<Integer> accountNumbers) {
        String sql = "DELETE FROM customers WHERE account_number = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int accNo : accountNumbers) {
                stmt.setInt(1, accNo);
                stmt.addBatch();
            }
            stmt.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUnitsConsumed(int customerAccountNumber) {
        String sql = "UPDATE customers SET units_consumed = " +
                     "(SELECT COALESCE(SUM(bi.quantity), 0) " +
                     " FROM bill_items bi " +
                     " JOIN bills b ON bi.bill_id = b.id " +
                     " WHERE b.customer_account_number = ?) " +
                     "WHERE account_number = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerAccountNumber);
            stmt.setInt(2, customerAccountNumber);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUnitsConsumedForBill(int customerAccountNumber, int totalUnits) {
        String sql = "UPDATE customers SET units_consumed = units_consumed + ? WHERE account_number = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, totalUnits);
            stmt.setInt(2, customerAccountNumber);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
