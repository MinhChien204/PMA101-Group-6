package com.example.less3.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.less3.R;
import com.example.less3.activity.MainActivity;
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
    ImageView anh;
    Uri uri;
    ImageSlider imageSlider;
    EditText edtSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rcv_TrangChu);
        anh = view.findViewById(R.id.imgAvata); // Assuming you have an ImageView in your layout
        edtSearch = view.findViewById(R.id.btnsearchProduct);
        imageSlider = view.findViewById(R.id.imgSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loadData();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = editable.toString().trim();
                searchProduct(keyword);
            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    String keyword = edtSearch.getText().toString().trim();
                    searchProduct(keyword);
                    hideKeyboard(v); // Ẩn bàn phím
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Kiểm tra xem edtSearch có từ khoá không và gọi lại searchProduct nếu có
        String keyword = edtSearch.getText().toString().trim();
        if (!keyword.isEmpty()) {
            searchProduct(keyword);
        }
    }

    void loadData() {
        Call<List<Clothes>> call = apiService.getClothes();

        call.enqueue(new Callback<List<Clothes>>() {
            @Override
            public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    clothesAdapter = new ClothesAdapter(list, getContext(), HomeFragment.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(clothesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Clothes>> call, Throwable t) {
                Log.d(TAG, "Load data failed: " + t.getMessage());
            }
        });
    }

    private void searchProduct(String keyword) {
        Call<List<Clothes>> call = apiService.searchCay(keyword);
        call.enqueue(new Callback<List<Clothes>>() {
            @Override
            public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {
                if (response.isSuccessful()) {
                    list.clear(); // Xóa danh sách hiện tại để cập nhật lại danh sách mới từ kết quả tìm kiếm
                    list.addAll(response.body()); // Thêm các phần tử mới từ kết quả tìm kiếm
                    clothesAdapter.notifyDataSetChanged(); // Thông báo cho adapter biết rằng dữ liệu đã thay đổi

                    if (list.isEmpty()) {
                        Toast.makeText(getContext(), "Không tìm thấy sản phẩm nào", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Đã xảy ra lỗi khi tìm kiếm", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Search failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Clothes>> call, Throwable t) {
                Log.e(TAG, "Search failed: " + t.toString());
                Toast.makeText(getContext(), "Đã xảy ra lỗi khi tìm kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
