package com.example.grocerystore.ui.cart;

import androidx.lifecycle.ViewModel;

import java.util.HashSet;

public class CartViewModel extends ViewModel {
    private final HashSet<String> addedCategories = new HashSet<>();

    public boolean isCategoryAdded(String categoryTitle) {
        return addedCategories.contains(categoryTitle);
    }

    public void addCategory(String categoryTitle) {
        addedCategories.add(categoryTitle);
    }
}
