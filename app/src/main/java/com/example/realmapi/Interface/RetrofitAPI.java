package com.example.realmapi.Interface;

import com.example.realmapi.Model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("e26569ef-8017-4d6e-818c-87df2a228f5d")
    Call<List<DataModel>> getCourse();
}
