package com.example.less3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.less3.R;
import com.example.less3.adapter.CartAdapter;

public class CartFragment extends Fragment {

    private ImageView emptyCartImage;
    private LinearLayout cartInfoLayout;
    private TextView itemCount;
    private TextView totalPrice;
    private Button paymentButton;
    RecyclerView recyclerView;

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
}