package com.example.tanamesaapp.ui.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.example.tanamesaapp.MainActivity;
import com.example.tanamesaapp.ar.HelloArActivity;
import com.example.tanamesaapp.models.Details;
import com.example.tanamesaapp.models.Order;
import com.example.tanamesaapp.ui.home.HomeActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanamesaapp.R;
import com.example.tanamesaapp.Utils;
import com.example.tanamesaapp.models.Meals;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.tanamesaapp.ui.detail.DetailView;

import java.util.Date;

import static com.google.android.gms.vision.L.TAG;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.mealThumb)
    ImageView mealThumb;
    
    @BindView(R.id.category)
    TextView category;
    
    @BindView(R.id.priceValue)
    TextView priceValue;
    
    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.ingredient)
    TextView ingredients;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    
    @BindView(R.id.youtube)
    TextView youtube;
    
    @BindView(R.id.source)
    TextView source;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActionBar();

        Intent intent = getIntent();
        String mealName = intent.getStringExtra(HomeActivity.EXTRA_DETAIL);
        String mealId = intent.getStringExtra(HomeActivity.EXTRA_ID);

        Log.w(TAG, "[DETAILS] mealName : " + mealName);
        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealById(mealId);

    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDetails(Details details) {
        Picasso.get().load(details.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(details.getStrMeal());
        category.setText(details.getStrCategory());
        priceValue.setText(details.getPrice());
        description.setText("Um " + details.getStrMeal() +" muito gostoso!");
        setupActionBar();

        //===

        if (details.getStrIngredient1() != null && !details.getStrIngredient1().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient1());
        }
        if (details.getStrIngredient2() != null && !details.getStrIngredient2().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient2());
        }
        if (details.getStrIngredient3() != null && !details.getStrIngredient3().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient3());
        }
        if (details.getStrIngredient4() != null && !details.getStrIngredient4().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient4());
        }
        if (details.getStrIngredient5() != null && !details.getStrIngredient5().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient5());
        }
        if (details.getStrIngredient6() != null && !details.getStrIngredient6().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient6());
        }
        if (details.getStrIngredient7() != null && !details.getStrIngredient7().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient7());
        }
        if (details.getStrIngredient8() != null && !details.getStrIngredient8().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient8());
        }
        if (details.getStrIngredient9() != null && !details.getStrIngredient9().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient9());
        }
        if (details.getStrIngredient10() != null &&!details.getStrIngredient10().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient10());
        }
        if (details.getStrIngredient11() != null &&!details.getStrIngredient11().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient11());
        }
        if (details.getStrIngredient12() != null &&!details.getStrIngredient12().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient12());
        }
        if (details.getStrIngredient13() != null &&!details.getStrIngredient13().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient13());
        }
        if (details.getStrIngredient14() != null &&!details.getStrIngredient14().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient14());
        }
        if (details.getStrIngredient15() != null &&!details.getStrIngredient15().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient15());
        }
        if (details.getStrIngredient16() != null && !details.getStrIngredient16().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient16());
        }
        if (details.getStrIngredient17() != null && !details.getStrIngredient17().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient17());
        }
        if (details.getStrIngredient18() != null && !details.getStrIngredient18().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient18());
        }
        if (details.getStrIngredient19() != null && !details.getStrIngredient19().isEmpty()) {
            ingredients.append("\n \u2022 " + details.getStrIngredient19());
        }


        youtube.setOnClickListener(v -> {
            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmar Pedido");
            builder.setMessage("Deseja mesmo pedir um "+ getIntent().getStringExtra(HomeActivity.EXTRA_DETAIL) + " ?");

            // add the buttons
            builder.setPositiveButton("Confirmo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseReference db;
                    db = FirebaseDatabase.getInstance().getReference("app/Orders");
                    Order order = new Order(MainActivity.table, getIntent().getStringExtra(HomeActivity.EXTRA_ID), getIntent().getStringExtra(HomeActivity.EXTRA_CATEGORY), new Date());
                    db.child(MainActivity.table).push().setValue(order);
                    Toast.makeText(getApplicationContext(), "Mesa : " + MainActivity.table + "\nSeu pedido foi encaminhado para o sistema! Obrigado", Toast.LENGTH_LONG)
                            .show();
                    finish();
                }
            });
            builder.setNegativeButton("Cancelar", null);

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        source.setOnClickListener(v -> {
//            Intent intentSource = new Intent(Intent.ACTION_VIEW);
//            intentSource.setData(Uri.parse(meal.getStrSource()));
//            startActivity(intentSource);
            Intent ARIntent = new Intent(this, HelloArActivity.class);
            startActivity(ARIntent);
        });


    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Error", message);
    }
}
