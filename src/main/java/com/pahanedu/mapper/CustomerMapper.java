package com.pahanedu.mapper;

import com.pahanedu.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {

    public Customer map(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setAccountNumber(rs.getInt("account_number"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setTelephone(rs.getString("telephone"));
        customer.setUnitsConsumed(rs.getInt("units_consumed"));
        return customer;
    }
}
