package com.example.tanamesaapp.ui.home;

import androidx.annotation.NonNull;
import android.util.Log;

import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Categories;
import com.example.tanamesaapp.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.vision.L.TAG;

class HomePresenter {

    private HomeView view;
    private DatabaseReference db;

    public HomePresenter(HomeView view) {
        this.view = view;
    }


    void getCategories() {
        view.showLoading();

        db = FirebaseDatabase.getInstance().getReference("app/Categories");
        List<Category> categories = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Category category = objSnapshot.getValue(Category.class);
                    Log.w(TAG, "onDataChange: CATEGORY" + category.getStrCategory() );
                    categories.add(category);
                }
                view.hideLoading();
                view.setCategories(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.hideLoading();
            }
        });
    }
}
