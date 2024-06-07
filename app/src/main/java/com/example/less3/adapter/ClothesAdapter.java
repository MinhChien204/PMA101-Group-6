package com.example.less3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.fragment.HomeFragment;
import com.example.less3.model.Clothes;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    List<Clothes> list;
    Context context;
    HomeFragment homeFragment;

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
    public void onBindViewHolder(@NonNull ClothesAdapter.ViewHolder holder, int position) {
        Clothes sv = list.get(position);

        holder.tvten.setText(sv.getName_cloth());
        holder.tvgia.setText(String.valueOf(sv.getPrice_cloth()));
        holder.tvbrand.setText(sv.getBrand());
        Glide.with(context)
                .load(sv.getImage_cloth())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvten, tvgia, tvbrand;
        ImageButton btnDelete, btnUpdate;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgAvata);
            tvten = itemView.findViewById(R.id.tvName);
            tvgia = itemView.findViewById(R.id.tvprice);
            tvbrand = itemView.findViewById(R.id.tvbrand);
//            btnDelete = itemView.findViewById(R.id.btnDeleteSv);
//            btnUpdate = itemView.findViewById(R.id.btn_EditSv);
        }
    }
}