package com.example.less3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.less3.R;
import com.example.less3.adapter.ProfileOptionAdapter;


public class ProfileFragment extends Fragment {
    ListView listView;
    private String[] options = {"Đơn Hàng Của Tôi", "Địa Chỉ Giao Hàng","Phương Thức Thanh Toán", "Cài Đặt", "Cửa Hàng"};
    private int[] icons = {R.drawable.ic_order, R.drawable.ic_address, R.drawable.ic_credit_card, R.drawable.ic_store, R.drawable.ic_store};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
            listView = view.findViewById(R.id.lv_profile_options);
        ProfileOptionAdapter adapter = new ProfileOptionAdapter(getContext(), options, icons);
        listView.setAdapter(adapter);


        return  view;
    }
}