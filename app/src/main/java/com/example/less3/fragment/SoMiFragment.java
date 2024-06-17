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
import com.example.less3.adapter.AosomiAdapter;
import com.example.less3.model.Aokhoac;
import com.example.less3.model.Aosomi;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SoMiFragment extends Fragment {

    ApiService apiService;
    RecyclerView recyclerView;
    AosomiAdapter aosomiAdapter;

    List<Aosomi> list;
    ImageView btnbacksomi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_so_mi, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }
        btnbacksomi = view.findViewById(R.id.btnbacksomi);
        recyclerView = view.findViewById(R.id.rcv_aosomi);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        btnbacksomi.setOnClickListener(new View.OnClickListener() {
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
        Call<List<Aosomi>> call = apiService.getAosomi();

        call.enqueue(new Callback<List<Aosomi>>() {
            @Override
            public void onResponse(Call<List<Aosomi>> call, Response<List<Aosomi>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    Log.d(TAG, "onResponse: " + list);
                    aosomiAdapter = new AosomiAdapter(getContext(),list );
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(aosomiAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Aosomi>> call, Throwable t) {
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