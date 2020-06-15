package com.example.tanamesaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderConfirmation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ListView listView;
    private BottomNavigationView menu;

    String titles[] = {"Pizza 4 Queijos", "Guaraná"};
    String descriptions[] = {"49,50", "5.50"};
    int images[] = {R.drawable.pizza, R.drawable.guarana};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        setupUI();

        listView = findViewById(R.id.listvieworderconfirm);
        MyOrderConfirmAdapter adapter = new MyOrderConfirmAdapter(this, titles, descriptions, images);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_notifications: {
                Intent intent = new Intent(this,CloseAccount.class);
                startActivity(intent);
            }
            case R.id.navigation_dashboard: {
                break;
            }
            case R.id.navigation_home: {
                break;
            }
        }
        return true;
    }


    private void setupUI(){
        menu = (BottomNavigationView) findViewById(R.id.nav_view);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment1);
//        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }


}

class MyOrderConfirmAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];
    int rImgs[];

    MyOrderConfirmAdapter (Context c, String title[], String description[], int imgs[]) {
        super(c, R.layout.order_row, R.id.titleText, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
        this.rImgs = imgs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.order_row, parent, false);
        ImageView images = row.findViewById(R.id.thumb);
        TextView myTitle = row.findViewById(R.id.titleText);
        TextView myDescription = row.findViewById(R.id.descriptionText);

        // now set our resources on views
        images.setImageResource(rImgs[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);
        return row;
    }
}