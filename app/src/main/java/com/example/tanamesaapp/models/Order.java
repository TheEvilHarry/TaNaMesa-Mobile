package com.example.tanamesaapp.models;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Order {

    @SerializedName("orders")
    @Expose
    private List<Product> orders = null;

    @SerializedName("table")
    @Expose
    private String  table;

    @SerializedName("productId")
    @Expose
    private String  productId;

    @SerializedName("productCategoryId")
    @Expose
    private String  productCategoryId;

    @SerializedName("date")
    @Expose
    private Date date;

    public Order(String table, String productId, String productCategoryId, Date date) {
        this.table = table;
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.date = date;
    }

    public List<Product> getOrders() {
        return orders;
    }

    public String getTable() {
        return table;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public Date getDate() {
        return date;
    }
}
