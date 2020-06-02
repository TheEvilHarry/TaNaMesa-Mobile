package com.example.tanamesaapp.models;

public enum CategoryEnum {
    PRATO("Prato"),
    LANCHE("Lanche"),
    BEBIDA("Bebida");

    private String category;

    CategoryEnum(String category){
        this.category=category;
    }

    public String getCategory(){
        return category;
    }
}
