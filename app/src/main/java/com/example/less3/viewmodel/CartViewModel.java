package com.example.less3.viewmodel;

// CartViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.less3.model.Clothes;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {
    private final MutableLiveData<List<Clothes>> cartItems = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Clothes>> getCartItems() {
        return cartItems;
    }

    public void addToCart(Clothes cloth) {
        List<Clothes> currentCartItems = cartItems.getValue();
        if (currentCartItems != null) {
            currentCartItems.add(cloth);
            cartItems.setValue(currentCartItems);
        }
    }
}
