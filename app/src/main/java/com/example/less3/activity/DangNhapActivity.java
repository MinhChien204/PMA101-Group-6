package com.example.less3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.less3.R;
import com.example.less3.model.User;
import com.example.less3.retrofit.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangNhapActivity extends AppCompatActivity {
    ApiService apiService;
    private User loggedInUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edUsername = findViewById(R.id.txt_user);
        EditText edPassword = findViewById(R.id.txt_password);

        // Cấu hình HttpLoggingInterceptor
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

        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String _username = edUsername.getText().toString().trim();
                String _password = edPassword.getText().toString().trim();
                user.setUsername(_username);
                user.setPassword(_password);

                Call<Response<User>> call = apiService.login(user);
                call.enqueue(new Callback<Response<User>>() {
                    @Override
                    public void onResponse(Call<Response<User>> call, Response<Response<User>> response) {
                        if (response.isSuccessful()) {
//                            User loggedInUser = response.body().getData();
                            // Store the user ID in SharedPreferences
//                            getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
//                                    .edit()
//                                    .putString("userId", loggedInUser.get_id())
//                                    .apply();

                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                        } else {
                            try {
                                Log.e("Login Error", "Error code: " + response.code() + ", Message: " + response.errorBody().string());
                            } catch (Exception e) {
                                Log.e("Login Error", "Exception while parsing error response");
                            }
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<User>> call, Throwable t) {
                        Log.e("Login Error", "Failure: " + t.getMessage());
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
