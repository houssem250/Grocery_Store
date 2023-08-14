package com.example.grocerystore.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerystore.R;
import com.example.grocerystore.ui.Product;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private int itemSpacing;

    public ViewAllAdapter(ArrayList<Product> products, int itemSpacing) {
        this.products = products;
        this.itemSpacing = itemSpacing;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_card, parent, false);

        // Apply item spacing to the view here
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = itemSpacing;
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImageView;
        private final TextView productTitleTextView;
        private final TextView productPriceTextView;
        private final ImageButton heartButton;

        // New method to set the heart icon based on product's favorite state
        private void setHeartIcon(ImageButton heartButton, boolean isFavorite) {
            if (isFavorite) {
                heartButton.setImageResource(R.drawable.baseline_favorite_24);
            } else {
                heartButton.setImageResource(R.drawable.baseline_favorite_border_24);
            }
        }

        public ViewHolder(View view) {
            super(view);
            productImageView = view.findViewById(R.id.productImageView);
            productTitleTextView = view.findViewById(R.id.productTitleTextView);
            productPriceTextView = view.findViewById(R.id.productPriceTextView);
            heartButton = view.findViewById(R.id.heartButton);
        }

        public void bind(Product product) {
            productImageView.setImageResource(product.getImageResource());
            productTitleTextView.setText(product.getName());
            productPriceTextView.setText("$" + product.getPrice());
            // Set heart icon click listener here
            // Use the product.isFavorite() to set the initial state
            setHeartIcon(heartButton, product.isFavorite());
        }
    }

}

