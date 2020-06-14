package com.example.tanamesaapp.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tanamesaapp.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    Context context;
    ListView listView;
    String titles[] = {"Pizza Macarena", "Guaraná", "Água Mineral", "Massa Primavera"};
    String descriptions[] = {"49,50", "5.50", "20,90", "49.60"};
    int images[] = {R.drawable.pizza, R.drawable.guarana, R.drawable.agua, R.drawable.massa};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

//        String data = String.valueOf(((MainAc)getActivity()).getTableID());

        final TextView textView = root.findViewById(R.id.tv);
        textView.setText("Total a pagar ");




        listView = root.findViewById(R.id.orderListView);

        MyAdapter adapter = new MyAdapter(context, titles, descriptions, images);
        listView.setAdapter(adapter);



        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity activity = getActivity();
                if (position ==  0) {
                    Toast.makeText(activity, "Facebook Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  1) {
                    Toast.makeText(activity, "Twitter Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  2) {
                    Toast.makeText(activity, "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  3) {
                    Toast.makeText(activity, "Youtube Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];
    int rImgs[];

    MyAdapter (Context c, String title[], String description[], int imgs[]) {
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