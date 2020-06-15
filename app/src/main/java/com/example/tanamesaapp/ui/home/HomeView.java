package com.example.tanamesaapp.ui.home;

import com.example.tanamesaapp.models.Categories;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
