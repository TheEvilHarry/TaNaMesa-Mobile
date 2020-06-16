package com.example.tanamesaapp.ui.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.example.tanamesaapp.ar.HelloArActivity;
import com.example.tanamesaapp.ui.home.HomeActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;
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
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.tanamesaapp.ui.detail.DetailView;

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

        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealById(mealName);
        
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
    public void setMeal(Meals.Meal meal) {
        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        category.setText(meal.getStrCategory());
        priceValue.setText("R$ 24.50");
        description.setText("Um " + meal.getStrMeal() +" muito gostoso!");
        setupActionBar();

        //===

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient1());
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient2());
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient3());
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient4());
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient5());
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient6());
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient7());
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient8());
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient9());
        }
        if (meal.getStrIngredient10() != null &&!meal.getStrIngredient10().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient10());
        }
        if (meal.getStrIngredient11() != null &&!meal.getStrIngredient11().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient11());
        }
        if (meal.getStrIngredient12() != null &&!meal.getStrIngredient12().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient12());
        }
        if (meal.getStrIngredient13() != null &&!meal.getStrIngredient13().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient13());
        }
        if (meal.getStrIngredient14() != null &&!meal.getStrIngredient14().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient14());
        }
        if (meal.getStrIngredient15() != null &&!meal.getStrIngredient15().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient15());
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient16());
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient17());
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient18());
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient19());
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient20());
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
                    Toast.makeText(getApplicationContext(), "Seu pedido foi encaminhado para o sistema! Obrigado", Toast.LENGTH_LONG)
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
