package com.example.less3.retrofit;

import com.example.less3.model.Clothes;
import com.example.less3.model.Type;
import com.example.less3.model.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String DOMAIN = "http://10.0.2.2:3000/";
    @GET("/")
    Call<List<Clothes>> getClothes();
    @GET("/type")
    Call<List<Type>> getType();
    @Multipart
    @POST("/register-send-email")
    Call<Response<User>> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part("phonenumber") RequestBody phonenumber,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part avartar
    );
    @POST("/login")
    Call<Response<User>> login (@Body User user);

    @GET("/search")
    Call<List<Clothes>> searchCay(@Query("key") String query);

}
