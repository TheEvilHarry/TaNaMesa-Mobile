package com.example.tanamesaapp.ui.home;

import com.example.tanamesaapp.models.Categories;
import com.example.tanamesaapp.models.Category;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void setCategories(List<Category> categories);
}
