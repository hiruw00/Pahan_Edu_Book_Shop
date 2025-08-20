package com.pahanedu.dao;

import com.pahanedu.business.user.mapper.UserMapper;
import com.pahanedu.business.user.model.Cashier;
import com.pahanedu.business.user.model.User;
import com.pahanedu.business.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final UserMapper userMapper = new UserMapper();

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return userMapper.map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertCashier(Cashier cashier) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cashier.getUsername());
            stmt.setString(2, cashier.getPassword());
            stmt.setString(3, cashier.getRole());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cashier> getAllCashiers() {
        List<Cashier> cashiers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'cashier'";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = userMapper.map(rs);
                if (user instanceof Cashier) {
                    cashiers.add((Cashier) user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashiers;
    }

    public boolean deleteCashier(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
