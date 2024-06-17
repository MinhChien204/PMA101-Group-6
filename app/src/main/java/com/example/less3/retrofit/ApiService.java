package com.example.less3.retrofit;

import com.example.less3.model.Address;
import com.example.less3.model.Aohoodie;
import com.example.less3.model.Aokhoac;
import com.example.less3.model.Aopolo;
import com.example.less3.model.Aosomi;
import com.example.less3.model.Cart;
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
    //API sản phẩm
    @GET("api/product")
    Call<List<Clothes>> getClothes();
    @GET("api/product/{id}")
    Call<Clothes> getClothDetails(@Path("id") String id);
    //APi địa chỉ
    @GET("api/type")
    Call<List<Type>> getType();
    @GET("api/address")
    Call<List<Address>> getAddress();
    @POST("api/add_address")
    Call<Void> addAddress(@Body Address address);
    @DELETE("api/del_address/{id}")
    Call<Void> deleteAddress(@Path("id") String addressId);
    //APi nguoi dung
    @GET("api/users")
    Call<List<User>> getUsers();
    @GET("api/users/{id}")
    Call<User> getUserDetails(@Path("id") String userId);
    @POST("api/users")
    Call<User> createUser(@Body User user);
    @PUT("api/users/{id}")
    Call<User> updateUser(@Path("id") String userId, @Body User user);
    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") String userId);
    //APi giỏ hàng
    @GET("api/cart")
    Call<List<Cart>> getCart();
    @POST("api/cart")
    Call<Void> addToCart(@Body Cart cartItem);
    @Multipart
    @POST("api/register-send-email")
    Call<Response<User>> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part("phonenumber") RequestBody phonenumber,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part avartar
    );
    @POST("api/login")
    Call<Response<User>> login (@Body User user);

    @GET("api/search")
    Call<List<Clothes>> searchCay(@Query("key") String query);

    @GET("api/khoac")
    Call<List<Aokhoac>> getAokhoac();
    @GET("api/hoodie")
    Call<List<Aohoodie>> getAohoodie();
    @GET("api/polo")
    Call<List<Aopolo>> getAopolo();
    @GET("api/somi")
    Call<List<Aosomi>> getAosomi();



}
