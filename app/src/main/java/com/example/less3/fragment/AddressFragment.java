package com.example.less3.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.less3.R;
import com.example.less3.activity.MainActivity;
import com.example.less3.adapter.AddressAdapter;
import com.example.less3.adapter.ClothesAdapter;
import com.example.less3.model.Address;
import com.example.less3.model.Clothes;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressFragment extends Fragment {
ApiService apiService;
AddressAdapter adapter;
RecyclerView rcv;
List<Address> list;
Button btnaddress;
ImageView imgback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }

        rcv = view.findViewById(R.id.rcv_newAddress);
        imgback = view.findViewById(R.id.back_address);
        btnaddress = view.findViewById(R.id.btn_addAddress);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        btnaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AddAddressFragment());
            }
        });

        return  view;
    }
    void loadData() {
        Call<List<Address>> call = apiService.getAddress();

        call.enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    adapter = new AddressAdapter(getContext(), list);
                    adapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
                        @Override
                        public void onDeleteClick(int position) {
                            deleteAddress(position);
                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                    rcv.setLayoutManager(gridLayoutManager);
                    rcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable throwable) {
                Log.e(TAG, "Failed to load addresses: " + throwable.getMessage());
            }
        });
    }

    private void deleteAddress(int position) {
        String addressId = list.get(position).get_id();
        Log.d(TAG, "Deleting address with ID: " + addressId); // Kiểm tra ID của địa chỉ
        Call<Void> call = apiService.deleteAddress(addressId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xóa thành công từ API, cập nhật lại RecyclerView hoặc danh sách dữ liệu
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                } else {
                    // Xử lý lỗi khi không xóa được địa chỉ
                    Log.e(TAG, "Failed to delete address. Code: " + response.code());
                    if (response.code() == 404) {
                        // Xử lý khi địa chỉ không tồn tại
                         Toast.makeText(getContext(), "Address không tồn tại ", Toast.LENGTH_SHORT).show();
                    } else {
                        // Xử lý các trường hợp lỗi khác nếu cần
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý lỗi khi không thể kết nối đến API
                Log.e(TAG, "Error deleting address: " + t.getMessage());
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
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}