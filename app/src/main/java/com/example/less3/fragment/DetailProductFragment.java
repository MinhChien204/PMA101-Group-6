package com.example.less3.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.activity.MainActivity;
import com.example.less3.model.Cart;
import com.example.less3.model.Clothes;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailProductFragment extends Fragment {
    View view;
    TextView tvName, tvPrice, tvbrand, tvmota, tvQuantity;
    ImageView imgAvatar;
    private String productId;
    ApiService apiService;
    ImageView imgback;
    Clothes cloth;

    Button btnaddtocart;



    // Variables for quantity and size selection
    int quantity = 1;
    String selectedSize = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cloth = (Clothes) getArguments().getSerializable("Chitietsanpham");
            Log.d("spct", "sanphamchitiet" + cloth);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        }
        imgback = view.findViewById(R.id.btn_backdetail);
        tvbrand = view.findViewById(R.id.brandDetailProduct);
        tvName = view.findViewById(R.id.NameDetailProduct);
        tvPrice = view.findViewById(R.id.PriceDetailProduct);
        tvmota = view.findViewById(R.id.motaDetailProduct);
        imgAvatar = view.findViewById(R.id.imgDetailProduct);
        tvQuantity = view.findViewById(R.id.tvQuantity);
        btnaddtocart = view.findViewById(R.id.btn_addtocart);
        AppCompatButton btnDecrease = view.findViewById(R.id.btnDecrease);
        AppCompatButton btnIncrease = view.findViewById(R.id.btnIncrease);

        // Size selection TextViews
        TextView sizeS = view.findViewById(R.id.sizeS_detailproduct);
        TextView sizeM = view.findViewById(R.id.sizeM_detailproduct);
        TextView sizeL = view.findViewById(R.id.sizeL_detailproduct);
        TextView sizeXL = view.findViewById(R.id.sizeXL_detailproduct);
        TextView sizeXXL = view.findViewById(R.id.sizeXXL_detailproduct);

        if (getArguments() != null) {
            productId = getArguments().getString("PRODUCT_ID");
        }

        getProductDetails(productId);

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    tvQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        View.OnClickListener sizeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset all sizes background
                sizeS.setBackgroundResource(R.drawable.size_background);
                sizeM.setBackgroundResource(R.drawable.size_background);
                sizeL.setBackgroundResource(R.drawable.size_background);
                sizeXL.setBackgroundResource(R.drawable.size_background);
                sizeXXL.setBackgroundResource(R.drawable.size_background);

                // Set selected size background
                TextView selectedSizeView = (TextView) v;
                selectedSize = selectedSizeView.getText().toString();
                selectedSizeView.setBackgroundResource(R.drawable.size_background_selected);
            }
        };

        sizeS.setOnClickListener(sizeClickListener);
        sizeM.setOnClickListener(sizeClickListener);
        sizeL.setOnClickListener(sizeClickListener);
        sizeXL.setOnClickListener(sizeClickListener);
        sizeXXL.setOnClickListener(sizeClickListener);

        return view;
    }

    private void getProductDetails(String productId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<Clothes> call = apiService.getClothDetails(productId);
        call.enqueue(new Callback<Clothes>() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if (response.isSuccessful()) {
                    Clothes clothes = response.body();
                    if (clothes != null) {
                        Glide.with(requireContext())
                                .load(clothes.getImage_cloth())
                                .into(imgAvatar);

                        tvbrand.setText(clothes.getBrand());
                        tvName.setText(clothes.getName_cloth());
                        tvPrice.setText(String.valueOf(clothes.getPrice_cloth())+ "$");
                        tvmota.setText(clothes.getMota());
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to load product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // DetailProductFragment.java
    private void addToCart() {
        Cart cartItem = new Cart();
        cartItem.setProductid_item(cloth.get_id());
        cartItem.setProductsize_item(selectedSize);
        cartItem.setProductquantity_item(quantity);
        cartItem.setProductImage_item(cloth.getImage_cloth());
        cartItem.setProductName_item(cloth.getName_cloth());
        cartItem.setProductPrice_item(cloth.getPrice_cloth());

        Call<Void> call = apiService.addToCart(cartItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Product added to cart", Toast.LENGTH_SHORT).show();
                    navigateToCart();  // Navigate to CartFragment after adding the product
                } else {
                    Toast.makeText(requireContext(), "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void navigateToCart() {
        CartFragment fragmentCart = new CartFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, fragmentCart)
                .addToBackStack(null)
                .commit();
    }
}
