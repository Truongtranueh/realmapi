package com.example.realmapi.Base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.realmapi.Base.Account.AccountFragment;
import com.example.realmapi.Base.History.HistoryFragment;
import com.example.realmapi.Base.Category.CategoryFragment;
import com.example.realmapi.Base.Home.CourseAdapter;
import com.example.realmapi.Base.Home.HomeFragment;
import com.example.realmapi.Base.Tranfer.TranferFragment;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.R;

import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    private CourseAdapter courseadapter;
    private ActivityMainBinding binding;
    private List<DataModel> dataModelList;
    private ViewModel viewModel;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationmenu.setOnItemSelectedListener(this);
        binding.bottomNavigationmenu.setSelectedItemId(R.id.home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        CategoryFragment categoryfragment = new CategoryFragment();
        HomeFragment homegragment = new HomeFragment();
        HistoryFragment historyFragment = new HistoryFragment();
        AccountFragment accountFragment = new AccountFragment();
        TranferFragment tranferFragment = new TranferFragment();
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.constrainmain, homegragment).commit();
                return true;
            case R.id.category:
                getSupportFragmentManager().beginTransaction().replace(R.id.constrainmain, categoryfragment).commit();
                return true;
            case R.id.history:
                getSupportFragmentManager().beginTransaction().replace(R.id.constrainmain, historyFragment).commit();
                return true;
            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.constrainmain, accountFragment).commit();
                return true;
            case R.id.tranfer:
                getSupportFragmentManager().beginTransaction().replace(R.id.constrainmain, tranferFragment).commit();
                return true;
        }
        return false;
    }
}