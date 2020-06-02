package com.example.tanamesaapp.models;

import java.util.List;

public class Order {

    private int id;
    private List<Product> products;
    private float orderTotal=0;

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getOrderTotal() {
        this.calculateOrderTotal();
        return orderTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void calculateOrderTotal(){
        float total=0;
        if(products.isEmpty()){
            orderTotal=0;
        }
        else{
            for(Product product:products)
                total+=product.getPrice();
            orderTotal=total;
        }
    }

    public void addProductToOrder(Product product){
        List<Product> productsOnTable = getProducts();
        productsOnTable.add(product);
        setProducts(productsOnTable);
        this.calculateOrderTotal();
    }
}
