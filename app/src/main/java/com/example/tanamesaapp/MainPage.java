package com.example.tanamesaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.tanamesaapp.ui.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);
        setupUI();

    }

    public void showMenu(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("indexNavbar", 1);
        intent.putExtra("tableNumber", "7");
        finish();
        startActivity(intent);
    }

    public void showBill(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("indexNavbar", 3);
        intent.putExtra("tableNumber", "7");
        finish();
        startActivity(intent);
    }

    public void showDashboard(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("indexNavbar", 2);
        intent.putExtra("tableNumber", "7");
        finish();
        startActivity(intent);
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

    public void callWaiter(View v){
        Intent intent = new Intent(this,CallWaiter.class);
        startActivity(intent);
    }

    private void setupUI(){
        menu = (BottomNavigationView) findViewById(R.id.nav_view);
        menu.setOnNavigationItemSelectedListener(this);
    }
}
