package com.example.grocerystore.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.grocerystore.R;
import com.example.grocerystore.databinding.FragmentCartBinding;
import com.example.grocerystore.ui.Product;
import com.example.grocerystore.ui.RowFragment;

import java.util.ArrayList;

public class CartFragment extends Fragment implements RowFragment.ViewAllClickListener {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayout cardLayout = root.findViewById(R.id.cardsLayout);

        // Initialize ViewModel
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Simulate data for demonstration
        ArrayList<Product> drinksProducts = new ArrayList<>();
        ArrayList<Product> fruitsProducts = new ArrayList<>();
        // Add more categories and products as needed

        // Populate products for each category
        for (int i = 0; i < 10; i++) {
            drinksProducts.add(new Product("Drink " + (i + 1), R.drawable.product_placeholder, 2.99));
            fruitsProducts.add(new Product("Fruit " + (i + 1), R.drawable.product_placeholder, 1.49));
        }

        // Add row fragments with products
        addRowFragmentIfNotAdded("Drinks", drinksProducts);
        addRowFragmentIfNotAdded("Fruits", fruitsProducts);
        // Add more rows as needed

        return root;
    }

    // Add a row fragment dynamically only if it hasn't been added before
    public void addRowFragmentIfNotAdded(String categoryTitle, ArrayList<Product> products) {
        if (!cartViewModel.isCategoryAdded(categoryTitle)) {
            cartViewModel.addCategory(categoryTitle);
            addRowFragment(categoryTitle, products);
        }
    }

    // Add a row fragment dynamically
    private void addRowFragment(String categoryTitle, ArrayList<Product> products) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        RowFragment fragment = RowFragment.newInstance(categoryTitle, products);
        fragment.setViewAllClickListener(this); // Set the callback listener
        transaction.add(R.id.cardsLayout, fragment);
        transaction.commit();
    }

    // Implementation of the "View All" callback from RowFragment
    @Override
    public void onViewAllClicked(String categoryTitle, ArrayList<Product> products) {
        /*
        // Clear the existing rows of products
        LinearLayout cardsLayout = requireView().findViewById(R.id.cardsLayout);
        cardsLayout.removeAllViews();*/

        // Create and add the ViewAllFragment to the cardsLayout
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ViewAllFragment fragment = ViewAllFragment.newInstance(
                categoryTitle,
                products,
                getResources().getDimensionPixelSize(R.dimen.view_all_item_spacing)
        );

        transaction.replace(R.id.cardsLayout, fragment) // Replace the cardsLayout with the ViewAllFragment
                .addToBackStack(null) // Add to back stack so it can be navigated back
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


