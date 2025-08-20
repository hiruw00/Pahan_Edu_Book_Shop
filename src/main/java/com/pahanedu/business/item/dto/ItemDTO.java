package com.pahanedu.business.item.dto;

public class ItemDTO {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String availability;

    public ItemDTO() {}

    public ItemDTO(int id, String name, double price, int quantity, String availability) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availability = availability;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
}

