package com.example.tanamesaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tanamesaapp.models.Order;
import com.example.tanamesaapp.models.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tableText;
    private TextView restaurantText;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String tableNumber = intent.getStringExtra("TableNumber");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        button = findViewById(R.id.button);
        tableText = findViewById(R.id.tableText);
        restaurantText = findViewById(R.id.restaurantText);

        String[] splittedQRText = tableNumber.split("/");
        String restaurantName = splittedQRText[1].replace("-"," ");
        restaurantText.setText(restaurantName);
        tableText.setText("Mesa " + splittedQRText[2]);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void openMainPage(View v){
        Log.d("TAG", "openMainPage: SOCORRO");
        db = FirebaseDatabase.getInstance().getReference("app");

        Order order = new Order();
        List<Product> products = new ArrayList<>();
        order.setProducts(products);
        order.setId(123);
        db.child("orders").child(Integer.toString(order.getId())).setValue(order)
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAAAAAAAAAAAAG", e.getLocalizedMessage());
            }});

        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }

}