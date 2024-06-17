package com.example.less3.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.less3.R;
import com.example.less3.activity.DangNhapActivity;
import com.example.less3.model.User;
import com.example.less3.retrofit.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    LinearLayout linearorder, linearaddress, linearpassword, linearProfile;
    Button btnlogout;
    ImageView imgAvatar;
    TextView tvname, tvEmail;
    ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgAvatar = view.findViewById(R.id.iv_profile_image);
        tvname = view.findViewById(R.id.tv_profile_name);
        tvEmail = view.findViewById(R.id.tv_profile_email);
        linearProfile = view.findViewById(R.id.profile_section);
        linearorder = view.findViewById(R.id.profile_myorder);
        linearaddress = view.findViewById(R.id.profile_address);
        linearpassword = view.findViewById(R.id.profile_changePassword);
        btnlogout = view.findViewById(R.id.btn_logout);

        // Initialize Retrofit and ApiService
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);

        String userId = getActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                .getString("userId", "");

        if (!userId.isEmpty()) {
            fetchUserProfile(userId);
        }

        linearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MyProfileFragment());
            }
        });

        linearorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MyOrderFragment());
            }
        });
        linearaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AddressFragment());
            }
        });
        linearpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ChangePasswordFragment());
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });
        return view;
    }

    private void fetchUserProfile(String userId) {
        Call<User> call = apiService.getUserDetails(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    tvname.setText(user.getName());
                    tvEmail.setText(user.getEmail());
                    // Load the user's profile image using Glide
                    Glide.with(ProfileFragment.this).load(user.getAvartar()).into(imgAvatar);
                } else {
                    Log.e("Profile Error", "Error code: " + response.code() + ", Message: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Profile Error", "Failure: " + t.getMessage());
            }
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                startActivity(intent);
                getActivity().finish(); // Close the current activity
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Close the dialog
            }
        });
        builder.show();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
