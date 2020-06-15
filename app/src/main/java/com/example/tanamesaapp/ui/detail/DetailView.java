package com.example.tanamesaapp.ui.detail;

import com.example.tanamesaapp.models.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
