package com.example.tanamesaapp.ui.detail;

import com.example.tanamesaapp.models.Details;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setDetails(Details details);
    void onErrorLoading(String message);
}
