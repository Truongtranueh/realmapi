package com.example.realmapi.Base.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.realmapi.Interface.RetrofitAPICity;
import com.example.realmapi.Model.District;
import com.example.realmapi.Model.Province;
import com.example.realmapi.R;
import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.ActivityPaymentBillBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentBillActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityPaymentBillBinding binding;
    private ViewModel viewModel;
    private List<Province> listprovince;
    private List<District> listdistrict;

    ArrayList<String> arrayListaa;
    ArrayList<String> arrayListnamedistric;
    ArrayList<String> arrayListnameward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityPaymentBillBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        listprovince = new ArrayList<>();
        arrayListaa = new ArrayList<>();

        binding.coursesspinner.setOnItemSelectedListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPICity retrofitAPICity = retrofit.create(RetrofitAPICity.class);
        Call<List<Province>> call = retrofitAPICity.getCity();
        call.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                if(response.body() != null){
                    binding.progresss.setVisibility(View.INVISIBLE);
                    for(int i = 0 ; i< response.body().size(); i++){
                        arrayListaa.add(response.body().get(i).getName());
                        listprovince.add(response.body().get(i));
                    }
                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListaa);
                    ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.coursesspinner.setAdapter(ad);
                }
            }
            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {

            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        listdistrict = new ArrayList<>();
        arrayListnamedistric = new ArrayList<>();
        for(int i = 0 ;i <listprovince.size(); i++){
            if(arrayListaa.get(position).toString().equals(listprovince.get(i).getName().toString())){
                //duyệt list lấy name huyện
                for(int j =0 ;j <listprovince.get(i).getDistricts().size(); j++){
                    arrayListnamedistric.add(listprovince.get(i).getDistricts().get(j).getName());
                    listdistrict.add(listprovince.get(i).getDistricts().get(j));
                }
            }
        }

        ArrayAdapter kt = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListnamedistric);
        kt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.coursesspinner2.setAdapter(kt);

        binding.coursesspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayListnameward = new ArrayList<>();
                // Duyet list lay name xa
                for(int i = 0; i< listdistrict.size(); i++){
                    if(arrayListnamedistric.get(position).toString().equals(listdistrict.get(i).getName())){
                        for(int j = 0; j< listdistrict.get(i).getWards().size(); j++){
                            arrayListnameward.add(listdistrict.get(i).getWards().get(j).getName());
                        }
                    }
                }
                ArrayAdapter aww = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListnameward);
                aww.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.coursesspinner3.setAdapter(aww);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void Changetype(ArrayList<String> a){
        ArrayAdapter add = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, a);
        add.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.coursesspinner2.setAdapter(add);
    }
}