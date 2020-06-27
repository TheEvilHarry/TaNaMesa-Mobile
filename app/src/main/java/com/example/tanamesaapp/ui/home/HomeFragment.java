package com.example.tanamesaapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tanamesaapp.R;
import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.adapter.RecyclerViewHomeAdapter;
import com.example.tanamesaapp.models.Category;
import com.example.tanamesaapp.ui.category.CategoryActivity;
import com.example.tanamesaapp.ui.category.CategoryPresenter;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tanamesaapp.ui.home.HomeActivity.EXTRA_CATEGORY;
import static com.example.tanamesaapp.ui.home.HomeActivity.EXTRA_POSITION;
import static com.google.android.gms.vision.L.TAG;

public class HomeFragment extends Fragment implements HomeView {

    private HomeViewModel homeViewModel;
    HomePresenter presenter;

    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerMeal;
    @BindView(R.id.recyclerCategory) RecyclerView recyclerViewCategory;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.w(TAG, "GOODBYE OMEGRAGMENT: ");

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(this);
        presenter.getCategories();
    }


    @Override
    public void showLoading() {
        getActivity().findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        try {
            getActivity().findViewById(R.id.shimmerCategory).setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCategories(List<Category> categories){
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(categories, getActivity());
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) categories);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getContext(), "Title", message);
    }

}