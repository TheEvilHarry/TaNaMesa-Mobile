package com.example.tanamesaapp.ui.category;

import com.example.tanamesaapp.models.Meals;
import com.example.tanamesaapp.models.Product;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setProducts(List<Product> products);
    void onErrorLoading(String message);
}
