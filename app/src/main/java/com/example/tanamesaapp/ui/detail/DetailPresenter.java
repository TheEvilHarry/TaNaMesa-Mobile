package com.example.tanamesaapp.ui.detail;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Details;
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

public class DetailPresenter {
    private DetailView view;
    private DatabaseReference db;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealById(String mealId) {
        
        view.showLoading();

        String URL = "app/Details/" + mealId;
        db = FirebaseDatabase.getInstance().getReference(URL);
        List<Details> detailsList = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Details details = objSnapshot.getValue(Details.class);
                    detailsList.add(details);
                }
                view.hideLoading();
                view.setDetails(detailsList.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.hideLoading();
            }
        });

        view.hideLoading();
        
    }
}
