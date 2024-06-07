package com.example.less3.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.less3.R;
import com.example.less3.adapter.ClothesAdapter;
import com.example.less3.model.Clothes;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    List<Clothes> list;
    ClothesAdapter clothesAdapter;
    ApiService apiService;
    EditText search;
    ClothesAdapter adapter;
    ImageView anh;
    Uri uri;
    ImageSlider imageSlider;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rcv_TrangChu);
        search = view.findViewById(R.id.edt_search);
        anh = view.findViewById(R.id.imgAvata); // Assuming you have an ImageView in your layout
        imageSlider = view.findViewById(R.id.imgSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3,ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        return view;
    }

    void loadData() {
        Call<List<Clothes>> call = apiService.getClothes();

        call.enqueue(new Callback<List<Clothes>>() {
            @Override
            public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    Log.d(TAG, "onResponse: " + list);
                    clothesAdapter = new ClothesAdapter(list, getContext(), HomeFragment.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(clothesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Clothes>> call, Throwable t) {
                Log.d("111", "lỗi:" + t.getMessage());
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            uri = data.getData();
//            anh.setImageURI(uri);
//        }
//    }

//    private void searchDistributor(String keyword) {
//        Call<List<Clothes>> call = apiService.searchCay(keyword);
//        call.enqueue(new Callback<List<Clothes>>() {
//            @Override
//            public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {
//                if (response.isSuccessful()) {
//                    list = response.body();
//
//                    adapter = new ClothesAdapter(list, getContext(), HomeFragment.this);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Clothes>> call, Throwable t) {
//                Log.e("Search", "Search failed: " + t.toString());
//                Toast.makeText(getContext(), "Đã xảy ra lỗi khi tìm kiếm", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
