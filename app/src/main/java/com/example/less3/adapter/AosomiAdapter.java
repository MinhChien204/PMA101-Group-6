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
import com.example.less3.model.Aopolo;
import com.example.less3.model.Aosomi;

import java.util.List;

public class AosomiAdapter extends RecyclerView.Adapter<AosomiAdapter.Viewhodler>{
    private Context context;
    private List<Aosomi> list;

    public AosomiAdapter(Context context, List<Aosomi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AosomiAdapter.Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aosomi, parent, false);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AosomiAdapter.Viewhodler holder, int position) {
        Aosomi aosomi = list.get(position);
        holder.tvNameAosomi.setText(aosomi.getName_somi());
        holder.tvprice_Aosomi.setText(String.valueOf(aosomi.getPrice_somi()));
        holder.tvbrand_aosomi.setText(aosomi.getBrand_somi());
        holder.tvmota_aosomi.setText(aosomi.getMota_somi());
        Glide.with(context)
                .load(aosomi.getImage_somi())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.imgAosomi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        TextView tvNameAosomi, tvprice_Aosomi, tvbrand_aosomi, tvmota_aosomi;
        ImageView imgAosomi;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            tvNameAosomi = itemView.findViewById(R.id.tvNameAosomi);
            tvprice_Aosomi = itemView.findViewById(R.id.tvprice_Aosomi);
            tvbrand_aosomi = itemView.findViewById(R.id.tvbrand_aosomi);
            tvmota_aosomi = itemView.findViewById(R.id.tvmota_aosomi);
            imgAosomi = itemView.findViewById(R.id.imgAosomi);
        }
    }
}
