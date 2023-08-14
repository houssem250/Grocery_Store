package com.example.grocerystore.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.grocerystore.R;

import java.util.ArrayList;

public class RowFragment extends Fragment {

    // Define a callback interface for the "View All" action
    public interface ViewAllClickListener {
        void onViewAllClicked(String categoryTitle, ArrayList<Product> products);
    }
    private ViewAllClickListener viewAllClickListener;
    private String categoryTitle;
    private ArrayList<Product> products;
    private static final String ARG_CATEGORY_TITLE = "categoryTitle";
    private static final String ARG_PRODUCTS = "products";

    public static RowFragment newInstance(String categoryTitle, ArrayList<Product> products) {
        RowFragment fragment = new RowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_TITLE, categoryTitle);
        args.putParcelableArrayList(ARG_PRODUCTS, products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_row, container, false);
        TextView titleTextView = view.findViewById(R.id.categoryCartTitleTxtV);
        TextView viewAllButton = view.findViewById(R.id.viewAllTextView);
        HorizontalScrollView horizontalScrollView = view.findViewById(R.id.horizontalScrollViewCards);
        LinearLayout cardLayout = view.findViewById(R.id.cardsLayout);

        Bundle args = getArguments();
        if (args != null) {
            String categoryTitle = args.getString(ARG_CATEGORY_TITLE);
            ArrayList<Product> products = args.getParcelableArrayList(ARG_PRODUCTS);

            titleTextView.setText(categoryTitle);

            for (Product product : products) {
                View cardView = inflater.inflate(R.layout.layout_product_card, cardLayout, false);
                ImageView productImageView = cardView.findViewById(R.id.productImageView);
                TextView productTitleTextView = cardView.findViewById(R.id.productTitleTextView);
                TextView productPriceTextView = cardView.findViewById(R.id.productPriceTextView);
                ImageButton heartButton = cardView.findViewById(R.id.heartButton);

                productImageView.setImageResource(product.getImageResource());
                productTitleTextView.setText(product.getName());
                productPriceTextView.setText("$" + product.getPrice());

                // Set initial state and click listener for the heart icon
                heartButton.setTag(product.isFavorite());
                setHeartIcon(heartButton, product.isFavorite());

                heartButton.setOnClickListener(v -> {
                    // Toggle the icon and update the isFavorite field
                    boolean isFavorite = !product.isFavorite();
                    product.setFavorite(isFavorite);
                    setHeartIcon(heartButton, isFavorite);
                });

                cardLayout.addView(cardView);
            }
        }

        // Set click listener for the "View All" TextView
        if (viewAllButton != null && viewAllClickListener != null) {
            viewAllButton.setOnClickListener(v -> {
                // Trigger the callback when "View All" is clicked
                viewAllClickListener.onViewAllClicked(categoryTitle, products);
            });
        }

        return view;
    }

    private void setHeartIcon(ImageButton heartButton, boolean isFavorite) {
        if (isFavorite) {
            heartButton.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            heartButton.setImageResource(R.drawable.baseline_favorite_border_24);
        }
    }

    // Setter for the ViewAllClickListener
    public void setViewAllClickListener(ViewAllClickListener listener) {
        this.viewAllClickListener = listener;
    }
}

