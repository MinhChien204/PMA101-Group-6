package com.example.less3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.less3.R;
import com.example.less3.adapter.CartAdapter;
import com.example.less3.model.Cart;
import com.example.less3.retrofit.ApiService;
import com.example.less3.retrofit.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartFragment extends Fragment {

    private ImageView emptyCartImage;
    private LinearLayout cartInfoLayout;
    private TextView itemCount;
    private TextView totalPrice;
    private Button paymentButton;
    RecyclerView recyclerView;
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        emptyCartImage = view.findViewById(R.id.empty_cart_image);
        cartInfoLayout = view.findViewById(R.id.cart_info);
        itemCount = view.findViewById(R.id.item_count);
        totalPrice = view.findViewById(R.id.total_price);
        paymentButton = view.findViewById(R.id.payment_button);
        recyclerView = view.findViewById(R.id.rcv_listcart);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ip)  // Replace with your backend base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create service interface for API endpoints
         apiService = retrofit.create(ApiService.class);

         loaddata();


        // Kiểm tra xem giỏ hàng có rỗng hay không
        if (cartIsEmpty()) {
            // Hiển thị ảnh giỏ hàng trống và ẩn thông tin giỏ hàng
            emptyCartImage.setVisibility(View.VISIBLE);
            cartInfoLayout.setVisibility(View.GONE);
        } else {
            // Ẩn ảnh giỏ hàng trống và hiển thị thông tin giỏ hàng
            emptyCartImage.setVisibility(View.GONE);
            cartInfoLayout.setVisibility(View.VISIBLE);

            // Cập nhật số lượng mặt hàng và tổng giá
            itemCount.setText(String.valueOf(getItemCount()));
            totalPrice.setText(getTotalPrice() + "đ");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final CartAdapter cartAdapter = new CartAdapter();
        recyclerView.setAdapter(cartAdapter);

        return view;
    }

    // Phương thức kiểm tra xem giỏ hàng có rỗng hay không
    private boolean cartIsEmpty() {
        // Thay thế logic này bằng code kiểm tra giỏ hàng thực tế
        return true; // Giả sử giỏ hàng rỗng
    }

    // Phương thức lấy số lượng mặt hàng trong giỏ hàng
    private int getItemCount() {
        // Thay thế logic này bằng code lấy số lượng mặt hàng thực tế
        return 0; // Giả sử có 0 mặt hàng
    }

    // Phương thức lấy tổng giá của giỏ hàng
    private String getTotalPrice() {
        // Thay thế logic này bằng code lấy tổng giá thực tế
        return "0"; // Giả sử tổng giá là 0
    }
    private void loaddata() {
        Call<List<Cart>> call = apiService.getCart();
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    List<Cart> cartItems = response.body();
                    if (cartItems != null && !cartItems.isEmpty()) {
//                        CartAdapter cartAdapter = new CartAdapter(cartItems);
//                        recyclerView.setAdapter(cartAdapter);

                        // Cập nhật các phần tử UI dựa trên dữ liệu đã lấy được
                        emptyCartImage.setVisibility(View.GONE);
                        cartInfoLayout.setVisibility(View.VISIBLE);
                        itemCount.setText(String.valueOf(getItemCount(cartItems)));
                        totalPrice.setText(getTotalPrice(cartItems) + "$");
                    } else {
                        // Xử lý khi giỏ hàng trống
                        emptyCartImage.setVisibility(View.VISIBLE);
                        cartInfoLayout.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(getContext(), "Lỗi khi tải thông tin giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Lỗi: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getItemCount(List<Cart> cartItems) {
        return cartItems.size();
    }

    private String getTotalPrice(List<Cart> cartItems) {
        double totalPrice = 0;
        for (Cart item : cartItems) {
//            totalPrice += item.getProductquantity_item() * item.getProductprice_item();
        }
        return String.format("%.2f", totalPrice);
    }

//    private String calculateTotalPrice(List<Cart> cartItems) {
//        // Implement your logic to calculate total price from cartItems
//        // Example:
//        double totalPrice = 0;
//        for (Cart item : cartItems) {
//            totalPrice += item.getPrice();
//        }
//        return String.format("%.2f", totalPrice); // Format to two decimal places
//    }
}