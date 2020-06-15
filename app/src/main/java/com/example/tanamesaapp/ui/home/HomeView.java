/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.tanamesaapp.ui.home;

import com.example.tanamesaapp.models.Categories;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
