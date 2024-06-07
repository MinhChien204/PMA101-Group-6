package com.example.less3.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.less3.R;
import com.example.less3.model.Clothes;

import java.util.Objects;


public class DetailProductFragment extends Fragment {
    View view;
    Clothes item;
    Uri selecturi;
Toolbar toolbar;
ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            item = (Clothes) getArguments().getSerializable("Chitietsanpham");
            Log.d("spct","sanphamchitiet" +item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_detail_product, container, false);
        toolbar = view.findViewById(R.id.ToolbarDetail);
        imageView = view.findViewById(R.id.imgDetailProduct);

        Glide.with(this)
                .load(R.drawable.ic_launcher_foreground) // Đường dẫn đến ảnh của bạn
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc với bán kính 20dp
                .into(imageView);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thông tin sản phẩm");

        return  view;
    }
}