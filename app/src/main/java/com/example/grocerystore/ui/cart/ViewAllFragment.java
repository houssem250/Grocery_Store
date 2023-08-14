package com.example.grocerystore.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.grocerystore.R;
import com.example.grocerystore.ui.Product;

import java.util.ArrayList;

public class ViewAllFragment extends Fragment {

    private static final String ARG_CATEGORY_TITLE = "categoryTitle";
    private static final String ARG_PRODUCTS = "products";
    private static final String ARG_ITEM_SPACING = "itemSpacing";

    public static ViewAllFragment newInstance(String categoryTitle, ArrayList<Product> products, int itemSpacing) {
        ViewAllFragment fragment = new ViewAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_TITLE, categoryTitle);
        args.putParcelableArrayList(ARG_PRODUCTS, products);
        args.putInt(ARG_ITEM_SPACING, itemSpacing);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);

        // Get arguments
        Bundle args = getArguments();
        if (args != null) {
            String categoryTitle = args.getString(ARG_CATEGORY_TITLE);
            ArrayList<Product> products = args.getParcelableArrayList(ARG_PRODUCTS);
            int itemSpacing = args.getInt(ARG_ITEM_SPACING);

            // Set up the RecyclerView with a LinearLayoutManager
            RecyclerView recyclerView = view.findViewById(R.id.viewAllRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // Create an adapter and set it to the RecyclerView
            ViewAllAdapter adapter = new ViewAllAdapter(products, itemSpacing);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}

