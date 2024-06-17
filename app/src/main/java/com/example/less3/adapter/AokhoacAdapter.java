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

import java.util.List;

public class AokhoacAdapter extends RecyclerView.Adapter<AokhoacAdapter.ViewHoler> {
    private Context context;
    private List<Aokhoac> list;

    public AokhoacAdapter(Context context, List<Aokhoac> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AokhoacAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aokhoac, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AokhoacAdapter.ViewHoler holder, int position) {
        Aokhoac aokhoac = list.get(position);
        holder.tvNameAokhoac.setText(aokhoac.getName_khoac());
        holder.tvprice_Aokhoac.setText(String.valueOf(aokhoac.getPrice_khoac()));
        holder.tvbrand_aokhoac.setText(aokhoac.getBrand_khoac());
        holder.tvmota_aokhoac.setText(aokhoac.getMota_khoac());
        Glide.with(context)
                .load(aokhoac.getImage_khoac())
                .thumbnail(Glide.with(context).load(R.drawable.img_10))
                .into(holder.imgAokhoac);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvNameAokhoac, tvprice_Aokhoac, tvbrand_aokhoac, tvmota_aokhoac;
        ImageView imgAokhoac;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvNameAokhoac = itemView.findViewById(R.id.tvNameAokhoac);
            tvprice_Aokhoac = itemView.findViewById(R.id.tvprice_Aokhoac);
            tvbrand_aokhoac = itemView.findViewById(R.id.tvbrand_aokhoac);
            tvmota_aokhoac = itemView.findViewById(R.id.tvmota_aokhoac);
            imgAokhoac = itemView.findViewById(R.id.imgAokhoac);

        }
    }
}
