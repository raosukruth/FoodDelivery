package com.example.fooddelivery;

public class CartItem {
    public String name;
    public String quantity;
    public String price;
    public CartItem() {
    }

    public CartItem(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
