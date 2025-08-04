package com.pahanedu.model;

public class BillItem {
    private int id;
    private int billId;
    private int itemId;
    private int quantity;
    private double price;

    public BillItem() {}

    public BillItem(int billId, int itemId, int quantity, double price) {
        this.billId = billId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

