package com.example.less3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.less3.R;
import com.example.less3.activity.MainActivity;


public class ChangePasswordFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_change_password, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}