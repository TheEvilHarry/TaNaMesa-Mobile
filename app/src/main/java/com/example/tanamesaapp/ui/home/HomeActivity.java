package com.example.tanamesaapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tanamesaapp.R;
import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Categories;
import com.example.tanamesaapp.adapter.RecyclerViewHomeAdapter;
import com.example.tanamesaapp.models.Category;
import com.example.tanamesaapp.ui.category.CategoryActivity;

import androidx.recyclerview.widget.GridLayoutManager;



public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_URL = "url";

    @BindView(R.id.viewPagerHeader) ViewPager viewPagerMeal;
    @BindView(R.id.recyclerCategory) RecyclerView recyclerViewCategory;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.getCategories();
    }

    @Override
    public void showLoading() { findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE); }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setCategories(List<Category> categories){
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(categories, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) categories);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

}
