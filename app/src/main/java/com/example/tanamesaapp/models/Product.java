package com.example.tanamesaapp.models;

public class Product {

    private int id;
    private String name;
    private float country;
    private String category;
    private boolean served=false;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setcountry(float country) {
        this.country = country;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getcountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }
}
