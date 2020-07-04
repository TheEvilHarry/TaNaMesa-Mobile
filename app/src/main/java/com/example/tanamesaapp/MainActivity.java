package com.example.tanamesaapp;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

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


    public static void setTable(String table) {
        MainActivity.table = table;
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
        int screenIndex = getScreenIndex(intent);

        //Método para mudar a view
        navController.navigate(screenIndex);
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
}