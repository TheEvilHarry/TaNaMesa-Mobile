package com.example.tanamesaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tanamesaapp.models.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.android.gms.vision.L.TAG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivty";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_URL = "url";
    public static String table;
    public static String restaurantName;
    DatabaseReference db;



    private void setTable(String table) {
        MainActivity.table = table;
        db = FirebaseDatabase.getInstance().getReference("app/Tables/");
        db.child(this.table).child("available").setValue(false);
    }

    public static void setRestaurantName(String restaurantName) {
        MainActivity.restaurantName = restaurantName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup de appbar, toolbar e navbar
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        Intent intent = getIntent();
        setTable(intent.getStringExtra("tableNumber"));
        setRestaurantName(intent.getStringExtra("restaurantName"));

        //Método para mudar a view
        navController.navigate(R.id.navigation_dashboard);

        FloatingActionButton fab = findViewById(R.id.waiterButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Garçom");
                builder.setMessage("Deseja mesmo chamar um garçom ?");

                // add the buttons
                builder.setPositiveButton("Confirmo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Aguarde que em breve um garçom irá lhe ajudar em breve, obrigado!", Toast.LENGTH_LONG)
                                .show();
                        callWaiter();
                    }
                });
                builder.setNegativeButton("Cancelar", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private int getScreenIndex(Intent intent) {
        int index;
        switch (intent.getIntExtra("indexNavbar", 1)){
            case 1:
                index =  R.id.navigation_home;
                break;
            case 2:
                index = R.id.navigation_dashboard;
                break;
            case 3:
                index = R.id.navigation_notifications;
                break;
            default:
                index = R.id.navigation_home;
        }

        return index;
    }

    private void callWaiter() {
        Log.w(TAG, "callWaiter: GARÇOMMMM ");
        db = FirebaseDatabase.getInstance().getReference("app/Tables/");
        db.child(this.table).child("needingWaiter").setValue(true);
    }
}