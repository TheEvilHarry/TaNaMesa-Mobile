package com.example.tanamesaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tableText;
    private TextView restaurantText;
    private DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String tableNumber = intent.getStringExtra("tableNumber");
        String restaurantName = intent.getStringExtra("restaurantName");


        BottomNavigationView navView = findViewById(R.id.nav_view);
        button = findViewById(R.id.button);
        tableText = findViewById(R.id.tableText);
        tableText.setText(tableText.getText() + " " + tableNumber);
        restaurantText = findViewById(R.id.restaurantText);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Método para mudar a view
        navController.navigate(R.id.navigation_notifications);
    }

    //COLOQUEM AQUI A ID DA MESA DO VIVENTE
    public void openMainPage(View v){
        Log.d("TAG", "openMainPage: SOCORRO");
        int id=0;
        Intent intent = new Intent(this,MainPage.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }

}