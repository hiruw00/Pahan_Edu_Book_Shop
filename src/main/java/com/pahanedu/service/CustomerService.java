package com.pahanedu.service;

import com.pahanedu.dao.CustomerDAO;
import com.pahanedu.model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public boolean updateCustomer(int originalAccountNumber, Customer updatedCustomer) {
        return customerDAO.updateCustomer(originalAccountNumber, updatedCustomer);
    }

    public boolean deleteCustomer(int accountNumber) {
        return customerDAO.deleteCustomer(accountNumber);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerByAccountNumber(int accountNumber) {
        return customerDAO.getCustomerByAccountNumber(accountNumber);
    }
}
