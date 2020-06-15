package com.example.tanamesaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.tanamesaapp.models.Product;
import com.example.tanamesaapp.models.Table;
import com.example.tanamesaapp.ui.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainPage";
    private DatabaseReference db;
    private int tableID;
    private Table table;
    private Product product;
    private BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);
//        startDatabase();
//        setupData();
        setupUI();
        menu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_notifications: {
                Intent intent = new Intent(this,CloseAccount.class);
                startActivity(intent);
                break;
            }
            case R.id.navigation_dashboard: {
                break;
            }
            case R.id.navigation_home: {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    private void startDatabase(){
        db = FirebaseDatabase.getInstance().getReference("app/Tables");
    }

    private void setupData(){
        tableID = getTableID();
        LoadTableFromDatabase();
        setTableListener();
    }

    private void setupUI(){
       menu = (BottomNavigationView) findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //below is part of the original thing:

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//
//        NavigationUI.setupWithNavController(menu, navController);



    }

    public int getTableID(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",-1);
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