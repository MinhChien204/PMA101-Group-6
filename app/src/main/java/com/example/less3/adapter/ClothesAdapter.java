package com.example.less3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.fragment.DetailProductFragment;
import com.example.less3.fragment.HomeFragment;
import com.example.less3.model.Clothes;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    List<Clothes> list;
    Context context;
    HomeFragment homeFragment;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ClothesAdapter(List<Clothes> list, Context context, HomeFragment homeFragment) {
        this.list = list;
        this.context = context;
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public ClothesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sv_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Clothes sv = list.get(position);

        holder.tvten.setText(sv.getName_cloth());
        holder.tvgia.setText(String.valueOf(sv.getPrice_cloth()));
        holder.tvbrand.setText(sv.getBrand());
        Glide.with(context)
                .load(sv.getImage_cloth())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.image);

        holder.linearProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(sv, holder.itemView.getContext());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvten, tvgia, tvbrand;
        ImageView image;
        LinearLayout linearProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgAvata);
            tvten = itemView.findViewById(R.id.tvName);
            tvgia = itemView.findViewById(R.id.tvprice);
            tvbrand = itemView.findViewById(R.id.tvbrand);
            linearProduct = itemView.findViewById(R.id.linearProduct);
        }
    }

    private void openFragment(final Clothes cloth, Context context) {
        if (context instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            Bundle bundle = new Bundle();
            bundle.putSerializable("Chitietsanpham", cloth);

            String productIdStr = cloth.get_id();  // Truyền _id dưới dạng chuỗi
            bundle.putString("PRODUCT_ID", productIdStr);

            DetailProductFragment frg = new DetailProductFragment();
            frg.setArguments(bundle);

            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.framelayout, frg);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
