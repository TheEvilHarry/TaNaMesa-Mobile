package com.example.tanamesaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CloseAccount extends AppCompatActivity {


    Context context;
    ListView listView;
    String titles[] = {"Pizza Macarena", "Guaraná", "Água Mineral", "Massa Primavera"};
    String descriptions[] = {"49,50", "5.50", "20,90", "49.60"};
    int images[] = {R.drawable.pizza, R.drawable.guarana, R.drawable.agua, R.drawable.massa};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_account);
        Toolbar toolbar = findViewById(R.id.mp_actionbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.orderListView);

        MyAccountAdapter adapter = new MyAccountAdapter(this,titles,descriptions,images);
        listView.setAdapter(adapter);

    }
}

class MyAccountAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];
    int rImgs[];

    MyAccountAdapter (Context c, String title[], String description[], int imgs[]) {
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