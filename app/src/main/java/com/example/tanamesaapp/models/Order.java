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

    @SerializedName("productName")
    @Expose
    private String  productName;

    @SerializedName("productCategoryId")
    @Expose
    private String  productCategoryId;

    @SerializedName("price")
    @Expose
    private String  price;

    @SerializedName("thumbURL")
    @Expose
    private String  thumbURL;

    @SerializedName("date")
    @Expose
    private Date date;

    public Order(String table, String productId, String productName, String productCategoryId, String price, String thumbURL, Date date) {
        this.table = table;
        this.productId = productId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.price = price;
        this.thumbURL = thumbURL;
        this.date = date;
    }

    public Order() {

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

    public String getPrice() { return price; }

    public String getThumbURL() { return thumbURL; }

    public String getProductName() { return productName; }

    public void setOrders(List<Product> orders) {
        this.orders = orders;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
