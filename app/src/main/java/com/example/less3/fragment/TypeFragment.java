package com.example.less3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.less3.R;
import com.example.less3.adapter.TypeAdapter;
import com.example.less3.model.Type;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeFragment extends Fragment {
    ApiService apiService;
    List<Type> list;
    TypeAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        recyclerView = view.findViewById(R.id.rcv_theloai);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        return view;
    }

    private void loadData() {
        Call<List<Type>> call = apiService.getType();
        call.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    if (list != null && !list.isEmpty()) {
                        adapter = new TypeAdapter(list, getContext(), TypeFragment.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable throwable) {
                // Handle failure here
            }
        });
    }
}
