package com.example.less3.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.less3.R;
import com.example.less3.activity.MainActivity;
import com.example.less3.adapter.AokhoacAdapter;
import com.example.less3.adapter.AopoloAdapter;
import com.example.less3.model.Aokhoac;
import com.example.less3.model.Aopolo;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PoloFragment extends Fragment {

    ApiService apiService;
    RecyclerView recyclerView;
    AopoloAdapter aopoloAdapter;

    List<Aopolo> list;
    ImageView btnbackpolo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_polo, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }
        btnbackpolo = view.findViewById(R.id.btnbackpolo);
        recyclerView = view.findViewById(R.id.rcv_aopolo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        btnbackpolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;



    }


    void loadData() {
        Call<List<Aopolo>> call = apiService.getAopolo();

        call.enqueue(new Callback<List<Aopolo>>() {
            @Override
            public void onResponse(Call<List<Aopolo>> call, Response<List<Aopolo>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    Log.d(TAG, "onResponse: " + list);
                    aopoloAdapter = new AopoloAdapter(getContext(),list );
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(aopoloAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Aopolo>> call, Throwable t) {
                Log.d("111", "lỗi:" + t.getMessage());
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}