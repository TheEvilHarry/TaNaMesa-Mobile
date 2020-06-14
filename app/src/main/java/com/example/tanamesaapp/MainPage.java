package com.example.tanamesaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tanamesaapp.models.Product;
import com.example.tanamesaapp.models.Table;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity {

    private DatabaseReference db;
    private int tableID;
    private Table table;
    private Product product;
    private String restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);
        startDatabase();
        setupData();
        setupUI();
    }

    private void startDatabase(){
        db = FirebaseDatabase.getInstance().getReference("app/Tables");
    }

    private void setupData(){
        tableID = getTableID();
        restaurant= getIntent().getStringExtra("restaurantName");
        LoadTableFromDatabase();
        setTableListener();
    }

    private void setupUI(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }



    public int getTableID(){
        Intent intent = getIntent();
        int id =0;
        String tableNumber = intent.getStringExtra("tableNumber");
        Log.d("HERE BTC", "" +tableNumber);
        id = Integer.valueOf(tableNumber);
        return id;
    }

    //This function is asyncronomous, which means we have to find a way to ask the app to wait() for the data somehow.
    private void LoadTableFromDatabase(){
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Table t = objSnapshot.getValue(Table.class);
                    if(t.getId() == tableID){
                        table=t;
                        Log.d("CHANGE",Integer.toString(t.getId()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //This function is asyncronomous, which means we have to find a way to ask the app to wait() for the data somehow.
    private void resetTableFromDatabase(DataSnapshot dataSnapshot){
        for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
            Table t = objSnapshot.getValue(Table.class);
            if(t.getId() == tableID){
                table=t;
                resetUIAfterDataChange(); //<==== Won't change anything while table isn't ready!!!
                Log.d("CHANGE",Integer.toString(t.getId()));
            }
        }
    }

    private void resetUIAfterDataChange(){
        //TODO
    }

    public void closeAccount(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("ID", "0");
        intent.putExtra("restaurantName", "Ponto UFRGS");
        intent.putExtra("Page","Account");
        startActivity(intent);
    }

    public void opensMenu(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("ID", "0");
        intent.putExtra("restaurantName", "Ponto UFRGS");
        intent.putExtra("Page","Menu");
        startActivity(intent);
    }


    public void setTableListener(){
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                resetTableFromDatabase(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}