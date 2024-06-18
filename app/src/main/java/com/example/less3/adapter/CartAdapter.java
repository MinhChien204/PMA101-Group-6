package com.example.less3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartItems;
    private Context context;
    private OnItemClickListener listener;

    public CartAdapter(List<Cart> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cartItem = cartItems.get(position);

        Glide.with(holder.itemView.getContext())
                .load(cartItem.getProductImage_item())
                .into(holder.imgProduct);
        holder.tvProductName.setText(cartItem.getProductName_item());
        holder.tvProductPrice.setText(String.valueOf(cartItem.getProductPrice_item()));
        holder.tvProductSize.setText(cartItem.getProductsize_item());
        holder.tvProductQuantity.setText(String.valueOf(cartItem.getProductquantity_item()));

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }

    public void removeCartItem(int position) {
        cartItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartItems.size());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, btnDelete;
        TextView tvProductName, tvProductPrice, tvProductSize, tvProductQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.product_name);
            tvProductSize = itemView.findViewById(R.id.product_size);
            tvProductPrice = itemView.findViewById(R.id.product_price);
            imgProduct = itemView.findViewById(R.id.product_image);
            tvProductQuantity = itemView.findViewById(R.id.quantity_text);
            btnDelete = itemView.findViewById(R.id.delete_button);
        }
    }
}
