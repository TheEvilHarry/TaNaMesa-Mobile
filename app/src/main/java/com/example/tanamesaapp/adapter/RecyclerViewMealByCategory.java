package com.example.tanamesaapp.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanamesaapp.R;
import com.example.tanamesaapp.models.Meals;
import com.example.tanamesaapp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder> {

    private List<Product> products;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewMealByCategory(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewMealByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealByCategory.RecyclerViewHolder viewHolder, int i) {

        String strMealThumb = products.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);

        String strMealName = products.get(i).getStrMeal();
        viewHolder.mealName.setText(strMealName);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMealByCategory.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
