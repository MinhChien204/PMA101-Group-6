package com.example.less3.adapter;

// CartAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.model.Clothes;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Clothes> cartItems = new ArrayList<>();

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Clothes cloth = cartItems.get(position);
        holder.tvName.setText(cloth.getName_cloth());
        holder.tvPrice.setText(String.valueOf(cloth.getPrice_cloth()) + "$");
        Glide.with(holder.itemView.getContext())
                .load(cloth.getImage_cloth())
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgAvatar,btnDelete;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.product_name);
            tvPrice = itemView.findViewById(R.id.product_size);
            imgAvatar = itemView.findViewById(R.id.product_image);
            btnDelete = itemView.findViewById(R.id.delete_button);
        }
    }
}
