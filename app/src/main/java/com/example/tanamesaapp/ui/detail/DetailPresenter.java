package com.example.tanamesaapp.ui.detail;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.vision.L.TAG;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealById(String mealName) {
        
        view.showLoading();
        
        Utils.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        Log.w(TAG, "getMealByCategory: mealsCall");
                        Log.w(TAG, "getMealByCategory: " + Utils.getApi().getMealByName(mealName).request().url().toString());
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setMeal(response.body().getMeals().get(0));
                        } else {
                            view.onErrorLoading(response.message());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
        
    }
}
