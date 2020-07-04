package com.example.tanamesaapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @SerializedName("category")
    @Expose
    String category;
    @SerializedName("categoryIndex")
    @Expose
    Long categoryIndex;
    @SerializedName("englishName")
    @Expose
    String englishName;
    @SerializedName("idMeal")
    @Expose
    String idMeal;
    @SerializedName("price")
    @Expose
    String price;
    @SerializedName("strMeal")
    @Expose
    String strMeal;
    @SerializedName("strMealThumb")
    @Expose
    String strMealThumb;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(Long categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}
