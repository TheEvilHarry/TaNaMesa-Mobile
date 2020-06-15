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
import com.example.tanamesaapp.ui.category.CategoryActivity;

import androidx.recyclerview.widget.GridLayoutManager;



public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

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
    public void showLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
//        public List<Categories> cat
        Categories.Category cat1 = new Categories.Category("1", "Bife", "https://www.themealdb.com/images/category/beef.png", "\n" +
                "Carne bovina é o nome culinário da carne de gado, principalmente do músculo esquelético. Os seres humanos comem carne desde os tempos pré-históricos. [1] A carne bovina é uma fonte de proteínas de alta qualidade e nutrientes essenciais. [2]");

        Categories.Category cat2 = new Categories.Category("2", "Frango", "https://www.themealdb.com/images/category/chicken.png", "Frango é um tipo de ave domesticada, uma subespécie de ave vermelha da selva. É um dos animais domésticos mais comuns e difundidos, com uma população total de mais de 19 bilhões em 2011. [1] Os seres humanos geralmente mantêm as galinhas como fonte de alimento (consumindo carne e ovos) e, mais raramente, como animais de estimação.");

        List<Categories.Category> sampleList = new ArrayList<>();
        sampleList.add(cat1);
        sampleList.add(cat2);

        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

}
