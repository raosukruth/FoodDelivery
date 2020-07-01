package com.example.fooddelivery;

public class MenuItem {
    public String availability;
    public String description;
    public String name;
    public String price;
    public String url;

    public MenuItem() {
    }

    public MenuItem(String availability, String description, String name, String price, String url) {
        this.availability = availability;
        this.description = description;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getAvailability() {
        return availability;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() { return url; }
}
