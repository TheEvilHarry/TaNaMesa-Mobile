package com.example.tanamesaapp.ui.category;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Category;
import com.example.tanamesaapp.models.Meals;
import com.example.tanamesaapp.models.Product;
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

public class CategoryPresenter {
    private CategoryView view;
    private DatabaseReference db;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }
    
    void getMealByCategory(String category) {
        view.showLoading();

        String URL = "app/Products/" + category;
        URL.replace(" ","%20");
        Log.w(TAG, "getMealByCategory: CARALHO CU CHEGUEI URL:" + URL);
        db = FirebaseDatabase.getInstance().getReference(URL);
        List<Product> products = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Product product = objSnapshot.getValue(Product.class);
                    products.add(product);
                }
                view.hideLoading();
                view.setProducts(products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.hideLoading();
            }
        });
        
    }
}
