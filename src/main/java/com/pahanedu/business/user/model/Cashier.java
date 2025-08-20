package com.pahanedu.business.user.model;

public class Cashier extends User {
    public Cashier(int id, String username, String password) {
        super(id, username, password, "cashier");
    }

    @Override
    public String getDashboardPath() {
        return "/cashier/dashboard";
    }
}
