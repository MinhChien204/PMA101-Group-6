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
import com.example.less3.model.Aohoodie;

import java.util.List;

public class AohoodieAdapter extends RecyclerView.Adapter<AohoodieAdapter.Viewholder>{

    private Context context;
    private List<Aohoodie> list;

    public AohoodieAdapter(Context context, List<Aohoodie> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AohoodieAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aohoodie, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AohoodieAdapter.Viewholder holder, int position) {
       Aohoodie aohoodie = list.get(position);
        holder.tvNameAohoodie.setText(aohoodie.getName_hoodie());
        holder.tvprice_Aohoodie.setText(String.valueOf(aohoodie.getPrice_hoodie()));
        holder.tvbrand_aohoodie.setText(aohoodie.getBrand_hoodie());
        holder.tvmota_aohoodie.setText(aohoodie.getMota_hoodie());
        Glide.with(context)
                .load(aohoodie.getImage_hoodie())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.imgAohoodie);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvNameAohoodie, tvprice_Aohoodie, tvbrand_aohoodie, tvmota_aohoodie;
        ImageView imgAohoodie;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvNameAohoodie = itemView.findViewById(R.id.tvNameAohoode);
            tvprice_Aohoodie = itemView.findViewById(R.id.tvprice_Aohoodie);
            tvbrand_aohoodie = itemView.findViewById(R.id.tvbrand_hoodie);
            tvmota_aohoodie = itemView.findViewById(R.id.tvmota_aohoodie);
            imgAohoodie = itemView.findViewById(R.id.imgAohoodie);
        }
    }
}
