package com.pahanedu.business.bill.model;

import java.util.Date;

public class Bill {
    private int id;
    private int customerAccountNumber;
    private int userId; // ✅ new field
    private double amount;
    private Date date;
    private String email;           // ✅ new field
    private String paymentMethod; 

    public Bill() {}

    // ✅ Updated constructor with userId
    public Bill(int id, int customerAccountNumber, int userId, double amount, Date date,String email, String paymentMethod) {
        this.id = id;
        this.customerAccountNumber = customerAccountNumber;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.email = email;
        this.paymentMethod = paymentMethod;
         
    }

    // ✅ Getter and setter for userId
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerAccountNumber() { return customerAccountNumber; }
    public void setCustomerAccountNumber(int customerAccountNumber) { this.customerAccountNumber = customerAccountNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
