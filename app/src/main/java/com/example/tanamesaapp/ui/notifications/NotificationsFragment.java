package com.example.tanamesaapp.ui.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tanamesaapp.MainActivity;
import com.example.tanamesaapp.R;
import com.example.tanamesaapp.ReaderPage;
import com.example.tanamesaapp.models.Order;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.vision.L.TAG;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    public static String amount;

    Context context;
    ListView listView;
    private DatabaseReference db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        Log.w(TAG, "GOODBYE NOTIFICATIONS: ");
//        String data = String.valueOf(((MainAc)getActivity()).getTableID());

        TextView priceView = root.findViewById(R.id.price);
        String tableNumber = ((MainActivity) getActivity()).table;
        Log.w(TAG, "onCreateView: MAGRO " + tableNumber);
        priceView.setText("Total A Pagar: R$ ");

        String URL = "app/Orders/" + tableNumber;
        Log.w(TAG, "onCreateView: MAGRO " + URL);
        db = FirebaseDatabase.getInstance().getReference(URL);
        List<Order> orders = new ArrayList<>();
        listView = root.findViewById(R.id.orderListView);

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Order order = objSnapshot.getValue(Order.class);
                    Log.w(TAG, "onDataChange: " + order.getProductName() );
                    orders.add(order);
                }

                fillListView(orders, listView);
                priceView.setText(amount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        Button closeAccountButton = root.findViewById(R.id.closeAccount);
        closeAccountButton.setOnClickListener(view -> {

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Deseja Fechar A Conta?");
            builder.setMessage("Sua mesa SERÁ LIBERADA e você NÃO PODERÁ FAZER MAIS PEDIDOS! O garçom irá em seguida lhe cobrar.");

            // add the buttons
            builder.setPositiveButton("Confirmo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(), "Obrigado pela visita e esperamos vê-lo novamente! Até mais", Toast.LENGTH_LONG)
                            .show();
                    closeAccount();
                }
            });
            builder.setNegativeButton("Cancelar", null);

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        return root;
    }

    private void fillListView(List<Order> orders, ListView listView){
        String productNames[] = new String[orders.size()];
        String urls[] = new String[orders.size()];
        String prices[] = new String[orders.size()];
        float amount = 0;

        for (int i = 0; i < orders.size(); i++) {
            productNames[i] = orders.get(i).getProductName();
            prices[i] = orders.get(i).getPrice();
            urls[i] = orders.get(i).getThumbURL();
            String priceStr = prices[i].replace("R$ ","");
            float price = Float.parseFloat(priceStr);
            amount += price;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        NotificationsFragment.amount =  decimalFormat.format(amount);

        MyAdapter adapter = new MyAdapter(context, productNames, urls, prices);
        listView.setAdapter(adapter);
    }

    private void closeAccount() {
        db = FirebaseDatabase.getInstance().getReference("app/Tables/");
        db.child(MainActivity.table).child("available").setValue(true);
        db.child(MainActivity.table).child("needingWaiter").setValue(false);
        db.child(MainActivity.table).child("waitingOrder").setValue(false);

        Intent intent = new Intent(getActivity(), ReaderPage.class);
        getActivity().finish();
        startActivity(intent);
    }
}

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    String productNames[];
    String urls[];
    String prices[];

    MyAdapter (Context c, String names[], String urls[], String prices[]) {
        super(c, R.layout.order_row, R.id.titleText, names);
        this.context = c;
        this.productNames = names;
        this.urls = urls;
        this.prices = prices;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.order_row, parent, false);
        ImageView thumbHolder = row.findViewById(R.id.thumb);
        TextView nameHolder = row.findViewById(R.id.titleText);
        TextView priceHolder = row.findViewById(R.id.descriptionText);

        // now set our resources on view
        nameHolder.setText(productNames[position]);
        priceHolder.setText(prices[position]);
        Picasso.get().load(urls[position]).into(thumbHolder);

        return row;
    }
}