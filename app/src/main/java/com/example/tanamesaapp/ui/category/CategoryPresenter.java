package com.example.tanamesaapp.ui.category;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.vision.L.TAG;

public class CategoryPresenter {
    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }
    
    void getMealByCategory(String category) {
        
        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealByCategory(category);

        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.setMeals(response.body().getMeals());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
        
    }
}
