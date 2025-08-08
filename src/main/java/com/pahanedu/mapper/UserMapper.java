package com.pahanedu.mapper;

import com.pahanedu.model.Admin;
import com.pahanedu.model.Cashier;
import com.pahanedu.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User map(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");

        if ("admin".equalsIgnoreCase(role)) {
            return new Admin(id, username, password);
        } else if ("cashier".equalsIgnoreCase(role)) {
            return new Cashier(id, username, password);
        } else {
            return null; // or throw exception if unknown role
        }
    }
}
