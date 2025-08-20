package com.pahanedu.business.user.model;

public class Admin extends User {
    public Admin(int id, String username, String password) {
        super(id, username, password, "admin");
    }

    @Override
    public String getDashboardPath() {
        return "/admin/dashboard";
    }
}
