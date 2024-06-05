package com.example.theofficeapp;

public class Character {
    private String name;
    private String description;
    private String image;

    public Character(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
