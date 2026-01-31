package com.pawpasta.se182138_lab4;

public class Item {
    private int image;
    private String name;
    private String description;
    private int price;

    public Item(int image, String name, String description, int price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}

