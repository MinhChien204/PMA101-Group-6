package com.example.less3.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.less3.R;
import com.example.less3.activity.MainActivity;
import com.example.less3.model.Address;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddAddressFragment extends Fragment {
    EditText edtname,edtsdt,edtaddress;
    Button btnaddAddress;
    ImageView imgback;
    ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_add_address, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }
        imgback= view.findViewById(R.id.btnbackAddress);
        edtname=view.findViewById(R.id.edtNameAddress);
        edtsdt=view.findViewById(R.id.edtPhoneAddress);
        edtaddress=view.findViewById(R.id.edtaddress);
        btnaddAddress = view.findViewById(R.id.btnthemAddress);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        btnaddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtname.getText().toString();
                String phone = edtsdt.getText().toString();
                String address = edtaddress.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ trường dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }

                addAddress(name, phone, address);
            }
        });
        return view;
    }
    private void addAddress(String name, String phone, String address) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.addAddress(new Address(name, phone, address));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Address added successfully", Toast.LENGTH_SHORT).show();
                    replaceFragment(new AddressFragment());
                } else {
                    Toast.makeText(getActivity(), "Failed to add address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AddAddressFragment", "Error: " + t.getMessage());
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Show BottomNavigationView when this fragment is destroyed
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}