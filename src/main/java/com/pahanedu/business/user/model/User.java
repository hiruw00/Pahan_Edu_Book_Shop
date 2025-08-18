package com.pahanedu.business.user.model;

public abstract class User {
    protected int id;
    protected String username;
    protected String password;
    protected String role; // "admin" or "cashier"

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public abstract String getDashboardPath();
}



