package com.pahanedu.business.customer.service;

import com.pahanedu.business.customer.dto.CustomerDTO;
import com.pahanedu.business.customer.model.Customer;
import com.pahanedu.dao.CustomerDAO;
import com.pahanedu.persistence.resource.factory.impl.CustomerFactoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final CustomerFactoryImpl customerFactory = new CustomerFactoryImpl();

    public void addCustomer(CustomerDTO dto) {
        Customer customer = customerFactory.create(
            dto.getAccountNumber(),
            dto.getName(),
            dto.getAddress(),
            dto.getTelephone(),
            dto.getUnitsConsumed()
        );
        customerDAO.addCustomer(customer);
    }

    public boolean updateCustomer(int originalAccountNumber, CustomerDTO dto) {
        Customer customer = customerFactory.create(
            dto.getAccountNumber(),
            dto.getName(),
            dto.getAddress(),
            dto.getTelephone(),
            dto.getUnitsConsumed()
        );
        return customerDAO.updateCustomer(originalAccountNumber, customer);
    }

    public boolean deleteCustomer(int accountNumber) {
        return customerDAO.deleteCustomer(accountNumber);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        return customers.stream()
            .map(c -> new CustomerDTO(
                c.getAccountNumber(),
                c.getName(),
                c.getAddress(),
                c.getTelephone(),
                c.getUnitsConsumed()))
            .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByAccountNumber(int accountNumber) {
        Customer c = customerDAO.getCustomerByAccountNumber(accountNumber);
        if (c == null) return null;
        return new CustomerDTO(
            c.getAccountNumber(),
            c.getName(),
            c.getAddress(),
            c.getTelephone(),
            c.getUnitsConsumed());
    }

    public void updateUnitsConsumed(int customerAccountNumber) {
        customerDAO.updateUnitsConsumed(customerAccountNumber);
    }

    public void updateUnitsConsumedForBill(int customerAccountNumber, int totalUnits) {
        customerDAO.updateUnitsConsumedForBill(customerAccountNumber, totalUnits);
    }
}
