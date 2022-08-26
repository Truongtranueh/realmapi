package com.example.realmapi.Interface;

import com.example.realmapi.Model.DataModel;
import com.example.realmapi.Model.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPICity {
    @GET("api/?depth=3")
    Call<List<Province>> getCity();
}
