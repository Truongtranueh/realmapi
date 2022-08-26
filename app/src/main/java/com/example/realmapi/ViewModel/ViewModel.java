package com.example.realmapi.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.realmapi.Interface.RetrofitAPI;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.Model.DataModel;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<List<DataModel>> datalist;
    Context context;
    private Realm realm;
    public ViewModel() {
        datalist = new MutableLiveData<>();
    }

    public MutableLiveData<List<DataModel>> getDatalistObserver() {
        return datalist;
    }

    public void getdata(){

        RetrofitAPI retrofitapi = Singleton.getRetrofit("https://run.mocky.io/v3/").create(RetrofitAPI.class);
        Call<List<DataModel>> call = retrofitapi.getCourse();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                datalist.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                datalist.postValue(null);
            }
        });
    }

    public void addnewDataToDatabase(int userID,String img, String title, String body){
        realm = Realm.getDefaultInstance();
        DataModel modal = new DataModel();
        Number id = realm.where(DataModel.class).max("id").longValue()+1;
        int nextId;
        if (id == null) {
            nextId = 1;
        } else {
            nextId = id.intValue() + 1;
        }
        modal.setId(nextId);
        modal.setUserId(userID);
        modal.setImage(img);
        modal.setTitle(title);
        modal.setBody(body);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(modal);
            }
        });
    }



    public void updateDataToDatabase(int userID,String img, String title, String body){
        realm = Realm.getDefaultInstance();
        DataModel modal = new DataModel();
        modal.setUserId(userID);
        modal.setImage(img);
        modal.setTitle(title);
        modal.setBody(body);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(modal);
            }
        });
    }

}
