package com.example.less3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.fragment.TypeFragment;
import com.example.less3.model.Type;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    List<Type> list;
    Context context;
    TypeFragment typeFragment;

    public TypeAdapter(List<Type> list, Context context, TypeFragment typeFragment) {
        this.list = list;
        this.context = context;
        this.typeFragment = typeFragment;
    }

    @NonNull
    @Override
    public TypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeAdapter.ViewHolder holder, int position) {
        Type type = list.get(position);
        Glide.with(context)
                .load(type.getImagetype())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.typeImg);
        }
    }
}
