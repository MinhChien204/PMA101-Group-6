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
import com.example.less3.model.Aokhoac;
import com.example.less3.model.Aopolo;
import com.google.android.play.core.integrity.ao;

import java.util.List;

public class AopoloAdapter extends RecyclerView.Adapter<AopoloAdapter.Viewholer>{
    private Context context;
    private List<Aopolo> list;

    public AopoloAdapter(Context context, List<Aopolo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AopoloAdapter.Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aopolo, parent, false);
        return new Viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AopoloAdapter.Viewholer holder, int position) {
        Aopolo aopolo = list.get(position);
        holder.tvNameAopolo.setText(aopolo.getName_polo());
        holder.tvprice_Aopolo.setText(String.valueOf(aopolo.getPrice_polo()));
        holder.tvbrand_aopolo.setText(aopolo.getBrand_polo());
        holder.tvmota_aopolo.setText(aopolo.getMota_polo());
        Glide.with(context)
                .load(aopolo.getImage_polo())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.imgAopolo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder{
        TextView tvNameAopolo, tvprice_Aopolo, tvbrand_aopolo, tvmota_aopolo;
        ImageView imgAopolo;
        public Viewholer(@NonNull View itemView) {
            super(itemView);
            tvNameAopolo = itemView.findViewById(R.id.tvNameAopolo);
            tvprice_Aopolo = itemView.findViewById(R.id.tvprice_Aopolo);
            tvbrand_aopolo = itemView.findViewById(R.id.tvbrand_aopolo);
            tvmota_aopolo = itemView.findViewById(R.id.tvmota_aopolo);
            imgAopolo = itemView.findViewById(R.id.imgAopolo);

        }
    }
}
